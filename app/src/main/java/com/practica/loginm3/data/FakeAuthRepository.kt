package com.practica.loginm3.data

import com.practica.loginm3.domain.AuthRepository
import kotlinx.coroutines.delay

/**
 * Implementación simulada de [AuthRepository] para el prototipo.
 * Acepta una única cuenta de prueba y simula la latencia de red.
 */
class FakeAuthRepository(
    private val latencyMs: Long = 1_200L,
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        delay(latencyMs)
        return if (email.equals(DEMO_EMAIL, ignoreCase = true) && password == DEMO_PASSWORD) {
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("Credenciales inválidas"))
        }
    }

    companion object {
        const val DEMO_EMAIL = "admin@demo.com"
        const val DEMO_PASSWORD = "Demo1234"
    }
}
