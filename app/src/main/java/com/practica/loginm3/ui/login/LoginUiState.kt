package com.practica.loginm3.ui.login

import androidx.annotation.StringRes

/** Estado inmutable de la pantalla de login. La UI solo dibuja lo que contiene este objeto. */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val formError: Int? = null,
)

/** Eventos de una sola vez que el ViewModel envía a la UI. */
sealed interface LoginEvent {
    data class Success(val email: String) : LoginEvent
}
