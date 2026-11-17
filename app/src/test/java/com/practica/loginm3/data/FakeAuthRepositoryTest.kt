package com.practica.loginm3.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeAuthRepositoryTest {

    private val repository = FakeAuthRepository(latencyMs = 0)

    @Test
    fun credenciales_correctas_devuelven_exito() = runBlocking {
        val result = repository.login("ADMIN@demo.com", "Demo1234")
        assertTrue(result.isSuccess)
    }

    @Test
    fun password_incorrecta_devuelve_fallo() = runBlocking {
        val result = repository.login("admin@demo.com", "otra-clave")
        assertTrue(result.isFailure)
    }
}
