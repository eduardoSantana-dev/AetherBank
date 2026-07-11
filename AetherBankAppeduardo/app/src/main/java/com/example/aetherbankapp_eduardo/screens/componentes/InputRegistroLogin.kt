package com.example.aetherbankapp_eduardo.screens.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


import com.example.aetherbankapp_eduardo.ui.theme.Azul

@Composable
fun inputLogin(value: String,onValueChange:(String)-> Unit,placeholder: String,tipo : KeyboardType){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Azul,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.0f),
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
            cursorColor = Azul
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = tipo
        )
    )
}
@Composable
fun inputLoginSenha(value: String,onValueChange:(String)-> Unit,placeholder: String){
    var senhaVisivel by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                placeholder,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.6f),
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.8f),
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Azul,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.0f),
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.12f),
            cursorColor = Azul
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation =
            if (senhaVisivel)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        trailingIcon = {

            IconButton(
                onClick = {
                    senhaVisivel = !senhaVisivel
                }
            ) {

                Icon(
                    imageVector =
                        if (senhaVisivel)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                    contentDescription = null
                )

            }

        }
    )
}

