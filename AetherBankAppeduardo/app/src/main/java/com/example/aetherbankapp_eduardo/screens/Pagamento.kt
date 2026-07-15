package com.example.aetherbankapp_eduardo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul
import com.example.aetherbankapp_eduardo.viewModel.LoginViewModel
import com.example.aetherbankapp_eduardo.viewModel.PagamentoViewModel

@Composable
fun Pagamento( navController: NavHostController){
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            tela3(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}
@Composable
fun tela3(modifier: Modifier,navController:NavHostController,vm: PagamentoViewModel = viewModel()){
    val chaveGet = navController.previousBackStackEntry
        ?.savedStateHandle?.get<String>("chave")

    if(chaveGet != null){
        vm.getChave(chaveGet)
    }

    Column(modifier = Modifier.padding(15.dp)) {
        Button(
            onClick = { navController.navigate(Routes.Home.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier.padding(top = 25.dp)
                .width(33.dp).height(33.dp).alpha(0.6f),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(42.dp)
            )
        }
        Text(
            text = "Qual valor deseja enviar para:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top =20.dp)
        )
        Text(
            text = vm.nomeFavorecido,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            modifier = Modifier.padding(top =20.dp),

        )
        Text(
            text = vm.chave,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier.padding(top =0.dp),
            color = Azul.copy(alpha = 0.8f)
        )
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 50.dp)) {
            Text(
                "R$",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,

            )
            OutlinedTextField(
                value = vm.inputValue,
                onValueChange = vm::onChangeValue ,

                placeholder = {
                    Text(
                        "0,00",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier.alpha(0.5F)
                    )
                },
                textStyle = TextStyle(

                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(0.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Azul
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
       Row() {
           Text(
               text = "Saldo em conta: ",
               fontWeight = FontWeight.SemiBold,
               fontSize = 13.sp,
               modifier = Modifier.padding(top =0.dp)
               .alpha(0.5f)
           )
           Text(
               text = vm.saldo,
               fontWeight = FontWeight.SemiBold,
               fontSize = 13.sp,
               modifier = Modifier.padding(top =0.dp),

           )
       }
        Button(
            onClick = {vm.transferir()},
            colors = ButtonDefaults.buttonColors(containerColor = Azul),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp)
                .height(50.dp),
            shape = RoundedCornerShape(20.dp)

        ){
            Text(
                text = "Finalizar transferência",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
    if (vm.mostrarErro) {

        AlertDialog(
            onDismissRequest = {},
            title = { Text("Chave inválida") },
            text = {
                Text("Insira uma chave válida")
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    onClick = {
                        navController.navigate(Routes.PagarChave.route)
                    }
                ) {
                    Text(
                        text = "Voltar",
                        color =Color.White
                    )
                }
            }
        )

    }
    if (vm.mostrarErroTrans) {

        AlertDialog(
            onDismissRequest = {},
            title = { Text("Erro ao realizar a transferência") },
            text = {
                Text(vm.textErro)
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    onClick = {
                        navController.navigate(Routes.PagarChave.route)
                    }
                ) {
                    Text(
                        text = "Voltar",
                        color =Color.White
                    )
                }
            }
        )

    }

    if (vm.mostrarSucesso) {

        AlertDialog(
            onDismissRequest = {},
            title = { Text("Transferência realizada com sucesso!") },
            text = {
                Text(vm.textSucesso)
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    onClick = {
                        navController.navigate(Routes.Home.route){
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Voltar",
                        color =Color.White
                    )
                }
            }
        )

    }
}

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewPagamento() {

    val navController = rememberNavController()

    AetherBankAppeduardoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            tela3(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )

        }
    }
}