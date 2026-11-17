package com.practica.loginm3.domain

import androidx.annotation.StringRes
import com.practica.loginm3.R

/** Reglas de validación del formulario. Cada función devuelve el id del mensaje de error o null si es válido. */
object LoginValidator {

    const val MIN_PASSWORD_LENGTH = 8

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    @StringRes
    fun validateEmail(email: String): Int? = when {
        email.isBlank() -> R.string.error_email_empty
        !EMAIL_REGEX.matches(email.trim()) -> R.string.error_email_invalid
        else -> null
    }

    @StringRes
    fun validatePassword(password: String): Int? = when {
        password.isEmpty() -> R.string.error_password_empty
        password.length < MIN_PASSWORD_LENGTH -> R.string.error_password_short
        else -> null
    }
}
