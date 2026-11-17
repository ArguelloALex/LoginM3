package com.practica.loginm3.domain

/** Contrato de autenticación. La interfaz permite cambiar la fuente de datos sin tocar la UI. */
interface AuthRepository {
    /** Devuelve [Result.success] si las credenciales son válidas y [Result.failure] en caso contrario. */
    suspend fun login(email: String, password: String): Result<Unit>
}
