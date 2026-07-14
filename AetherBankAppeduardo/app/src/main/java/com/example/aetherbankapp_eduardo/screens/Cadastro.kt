package com.example.aetherbankapp_eduardo.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.R
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul
import com.example.aetherbankapp_eduardo.screens.componentes.inputLogin
import com.example.aetherbankapp_eduardo.screens.componentes.inputLoginSenha
import com.example.aetherbankapp_eduardo.viewModel.CadastroViewModel

@Composable
fun Cadastro(navController: NavHostController){
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TelaCadastro(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
fun TelaCadastro(modifier: Modifier, navController: NavHostController, vm : CadastroViewModel = viewModel() ){
    Surface(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment =Alignment.CenterHorizontally) {
            Row(modifier = Modifier.height(130.dp)){

                Image(
                    painter = painterResource(
                        if(isSystemInDarkTheme()){
                            R.drawable.logo_completa_dark
                        }else {
                            R.drawable.logo_completa
                        }
                    ),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    alpha = 1f,
                    colorFilter = null ,
                    modifier = Modifier.size(300.dp).
                    padding(top =0.dp)
                )
            }
            Column(modifier = Modifier.fillMaxWidth(0.85f)) {
                Text(
                    text = "Crie sua conta Aether",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                inputLogin(vm.nome,vm::nomeChange,"Nome", KeyboardType.Text)
                inputLogin(vm.email,vm::emailChange,"Email",KeyboardType.Email)
                inputLogin(vm.cpf,vm::cpfChange,"CPF",KeyboardType.Text)
                inputLogin(vm.numero,vm::numeroChange,"Número",KeyboardType.Phone)
                inputLoginSenha(vm.senha,vm::senhaChange,"Senha")
                inputLoginSenha(vm.senhaConfirm,vm::senhaConfirmChange,"Confirme a senha")

                Button(
                    onClick = vm::cadastrar,
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20.dp)

                ){
                    Text(
                        text = "Cadastrar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {navController.navigate(Routes.Login.route)},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier

                            .padding(top = 10.dp)
                            ,
                        shape = RoundedCornerShape(20.dp)

                    ){
                        Text(
                            text = "Já tenho uma conta",
                            fontSize = 15.sp,
                            color = Azul
                        )
                    }
                }
            }
            if (vm.mostrarErro) {

                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("Erro ao realizar cadastro") },
                    text = {
                        Text(vm.alertT)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Azul),
                            onClick = {
                                vm.fechar()
                            }
                        ) {
                            Text("OK")
                        }
                    }
                )

            }
            if (vm.alertCadastrado) {

                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("Cadastro realizado com sucesso") },
                    text = {
                        Text("Agora você é um aether")
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Azul),
                            onClick = {
                                navController.navigate(Routes.Login.route)
                            }
                        ) {
                            Text(
                                text = "Fazer Login",
                                color = Color.White
                            )
                        }
                    }
                )

            }
        }

    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewCadastro(){
    val navController = rememberNavController()
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TelaCadastro(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}