package com.practica.loginm3.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.practica.loginm3.ui.home.HomeScreen
import com.practica.loginm3.ui.login.LoginScreen
import com.practica.loginm3.ui.theme.LoginM3Theme

/**
 * Raíz de la aplicación. Con solo dos pantallas, la navegación se resuelve con estado;
 * en una app mayor se sustituiría por Navigation Compose.
 */
@Composable
fun LoginM3App() {
    var dynamicColor by rememberSaveable { mutableStateOf(true) }
    var userEmail by rememberSaveable { mutableStateOf<String?>(null) }

    LoginM3Theme(dynamicColor = dynamicColor) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            AnimatedContent(
                targetState = userEmail != null,
                label = "root",
            ) { isLoggedIn ->
                if (isLoggedIn) {
                    HomeScreen(
                        email = userEmail.orEmpty(),
                        dynamicColor = dynamicColor,
                        onDynamicColorChange = { dynamicColor = it },
                        onLogout = { userEmail = null },
                    )
                } else {
                    LoginScreen(onLoginSuccess = { userEmail = it })
                }
            }
        }
    }
}
