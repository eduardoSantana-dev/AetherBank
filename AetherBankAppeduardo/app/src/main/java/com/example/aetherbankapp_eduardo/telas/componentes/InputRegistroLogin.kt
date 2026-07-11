package com.example.aetherbankapp_eduardo.telas.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


import com.example.aetherbankapp_eduardo.ui.theme.Azul

@Composable
fun inputLogin(value: String,onValueChange:(String)-> Unit,placeholder: String){
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
        )
    )
}