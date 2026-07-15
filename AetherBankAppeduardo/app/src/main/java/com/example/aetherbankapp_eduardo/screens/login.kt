package com.example.aetherbankapp_eduardo.screens



import android.R.attr.alpha
import android.graphics.Color.alpha
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.R
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul
import com.example.aetherbankapp_eduardo.screens.componentes.inputLogin
import com.example.aetherbankapp_eduardo.screens.componentes.inputLoginSenha
import com.example.aetherbankapp_eduardo.viewModel.LoginViewModel

@Composable
fun Login(navController: NavHostController){
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TelaLogin(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
fun TelaLogin(modifier: Modifier, navController: NavHostController, vm: LoginViewModel = viewModel()){
    if(vm.logado){
        navController.navigate(Routes.Home.route){
            popUpTo(0) {
                inclusive = true
            }
        }
    }


    Surface(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment =Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.height(130.dp)){

                Image(
                    painter = painterResource(
                        if(isSystemInDarkTheme()){
                            R.drawable.logo
                        }else {
                            R.drawable.logo_completa
                        }
                    ),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    alpha = 1f,
                    colorFilter = null ,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(top = 0.dp)
                )
            }
            Column(modifier = Modifier.fillMaxWidth(0.85f)) {

                inputLogin(vm.email,vm::emailChange,"Email", KeyboardType.Email)
                inputLoginSenha(vm.senha,vm::senhaChange,"Senha")

                Button(
                    onClick = vm::login,
                    colors = ButtonDefaults.buttonColors(containerColor = Azul),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20.dp)

                ){
                    Text(
                        text = "Entrar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {navController.navigate(Routes.Cadastro.route)},
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier

                            .padding(top = 10.dp)
                        ,
                        shape = RoundedCornerShape(20.dp)

                    ){
                        Text(
                            text = "Não tenho uma conta",
                            fontSize = 15.sp,
                            color = Azul
                        )
                    }
                }
            }
            if (vm.mostrarErro) {

                AlertDialog(
                    onDismissRequest = {},
                    title = { Text("Erro ao realizar login") },
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
                            Text(
                                text = "OK",
                                color =Color.White
                            )
                        }
                    }
                )

            }
        }
        if(vm.loading){
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha =0.5f)),
                contentAlignment = Alignment.Center,

                ){
                CircularProgressIndicator(color = Azul)
            }
        }
    }
}

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewLogin(){
    val navController = rememberNavController()
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            TelaLogin(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}