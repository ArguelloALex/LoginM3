package com.practica.loginm3.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.loginm3.R
import com.practica.loginm3.data.FakeAuthRepository
import com.practica.loginm3.domain.AuthRepository
import com.practica.loginm3.domain.LoginValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    /** Constructor sin argumentos para que viewModel() pueda crear la instancia con la fábrica por defecto. */
    constructor() : this(FakeAuthRepository())

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<LoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun onEmailChange(value: String) {
        _uiState.update { it.copy(email = value, emailError = null, formError = null) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value, passwordError = null, formError = null) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
    }

    fun onRememberMeChange(value: Boolean) {
        _uiState.update { it.copy(rememberMe = value) }
    }

    fun onLoginClick() {
        val current = _uiState.value
        if (current.isLoading) return

        val emailError = LoginValidator.validateEmail(current.email)
        val passwordError = LoginValidator.validatePassword(current.password)
        if (emailError != null || passwordError != null) {
            _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, formError = null) }
            val email = current.email.trim()
            repository.login(email, current.password).fold(
                onSuccess = {
                    // "Recordarme" conserva el correo solo mientras la app siga abierta (sin persistencia en disco).
                    _uiState.value = LoginUiState(
                        email = if (current.rememberMe) email else "",
                        rememberMe = current.rememberMe,
                    )
                    _events.trySend(LoginEvent.Success(email))
                },
                onFailure = {
                    _uiState.update { it.copy(isLoading = false, formError = R.string.error_credentials) }
                },
            )
        }
    }
}
