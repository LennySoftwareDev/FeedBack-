package com.appdev.feedback.ui.screens.login.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdev.feedback.ui.components.OutlinedTextFieldComponent

@Composable
fun LoginContent(
    username: String,
    onUsernameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    isLoginEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = androidx.compose.ui.graphics.Color(0xFF3DDC84))
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "FeedBack +",
                fontSize = 48.sp,
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            OutlinedTextFieldComponent(
                value = username,
                onValueChange = onUsernameChange,
                label = "Usuario",
                placeholder = "Ingrese su usuario",
                leadingIcon = null,
                keyboardType = KeyboardType.Text,
                errorMessage = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldComponent(
                value = password,
                onValueChange = onPasswordChange,
                label = "Contraseña",
                placeholder = "Ingrese su contraseña",
                leadingIcon = null,
                keyboardType = KeyboardType.Password,
                isPassword = true,
                errorMessage = null
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onLoginClick,
                enabled = isLoginEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Iniciar sesión",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}
