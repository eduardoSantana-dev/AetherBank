package com.example.aetherbankapp_eduardo.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul

@Composable
fun PagarChave( navController: NavHostController){
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            tela2(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
fun tela2(modifier: Modifier,navController:NavHostController){
    var email by remember { mutableStateOf("") }

    navController.currentBackStackEntry
        ?.savedStateHandle
        ?.set("chave", email)
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(15.dp)) {
            Button(
                onClick = {navController.navigate(Routes.Home.route)},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(top = 25.dp)
                    .width(33.dp).height(33.dp).alpha(0.6f),
                contentPadding = PaddingValues(0.dp)
            ) { Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(42.dp)
            )}
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
                , modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Pagar com chave pix",
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            "Email, telefone ou CPF"
                        )
                    },
                    textStyle = TextStyle(
                        color = Azul,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Azul,
                        unfocusedBorderColor = Color.Gray,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Azul
                    )
                )
                Button(
                    onClick = {navController.navigate(Routes.Pagamento.route)},
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 500.dp)
                        .height(60.dp),
                            shape = RoundedCornerShape(20.dp)

                ){
                        Text(
                            text = "Continuar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.White
                        )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewPegarChave() {

    val navController = rememberNavController()

    AetherBankAppeduardoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            tela2(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )

        }
    }
}