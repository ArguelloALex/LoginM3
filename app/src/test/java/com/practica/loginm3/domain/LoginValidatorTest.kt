package com.practica.loginm3.domain

import com.practica.loginm3.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LoginValidatorTest {

    @Test
    fun email_vacio_devuelve_error_de_vacio() {
        assertEquals(R.string.error_email_empty, LoginValidator.validateEmail("   "))
    }

    @Test
    fun email_sin_arroba_devuelve_error_de_formato() {
        assertEquals(R.string.error_email_invalid, LoginValidator.validateEmail("correo.com"))
    }

    @Test
    fun email_valido_no_devuelve_error() {
        assertNull(LoginValidator.validateEmail(" alumno@correo.com "))
    }

    @Test
    fun password_vacia_devuelve_error_de_vacio() {
        assertEquals(R.string.error_password_empty, LoginValidator.validatePassword(""))
    }

    @Test
    fun password_corta_devuelve_error_de_longitud() {
        assertEquals(R.string.error_password_short, LoginValidator.validatePassword("abc123"))
    }

    @Test
    fun password_con_longitud_minima_es_valida() {
        assertNull(LoginValidator.validatePassword("12345678"))
    }
}
