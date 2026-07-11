package com.example.aetherbankapp_eduardo.telas

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pix
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.aetherbankapp_eduardo.R
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul
import com.example.aetherbankapp_eduardo.ui.theme.Verde

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.navigation.Routes
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavHostController) {
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Tela(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}



@Composable
fun Tela(modifier: Modifier, navController: NavHostController) {



    var saldo by remember { mutableStateOf("12.200.67") }
    fun buscarSaldo() = runBlocking{
        val client =  HttpClient(CIO)
        CoroutineScope(Dispatchers.IO).launch {
            val result = client.get("http://192.168.0.9:8080/BuscarSaldo/1")
            saldo = result.body<String>()
            client.close()
        }
    }

    var saldoView by remember { mutableStateOf("••••") }
    fun verSaldo(){
        if(saldoView == "••••"){
                saldoView = saldo
        }else{
            saldoView = "••••"
        }
    }

    Surface(modifier = Modifier.fillMaxSize()){
        Column() {
            Column(
                modifier = Modifier.background( color = Azul)
                    .fillMaxWidth()
                    .height(195.dp)
                    .padding(start = 10.dp,end = 10.dp, top = 15.dp)


            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                    ,modifier = Modifier.fillMaxWidth().height(100.dp)

                ){
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        alpha = 1f,
                        colorFilter = null ,
                        modifier = Modifier.size(133.dp).
                        padding(bottom =20.dp)
                    )
                    Row(horizontalArrangement = Arrangement.SpaceAround,
                        modifier =  Modifier.width(100.dp)

                    ){
                        Button(
                            onClick = {verSaldo()},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.padding(top = 25.dp)
                                .width(33.dp).height(33.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) { Icon(
                            imageVector = Icons.Default.RemoveRedEye,
                            contentDescription = "Home",
                            tint = Color.Black
                        )}
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.padding(top = 25.dp)
                                .width(33.dp).height(33.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) { Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Home",
                            tint = Color.Black
                        )}
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Saldo em conta",
                        modifier = Modifier.alpha(0.6F),
                        color = Color.White,
                        fontWeight = FontWeight(500)

                    )
                    Text(
                        text = "R$ "+ saldoView,
                        modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight(900),
                        color = Color.White,
                        fontSize = 35.sp

                    )

                }

            }
           Row(
               horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()

           ){
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                  Button(
                      onClick = {navController.navigate(Routes.PagarChave.route)},
                      colors = ButtonDefaults.buttonColors(containerColor = Azul.copy(alpha = 0.2f)),

                      modifier = Modifier.padding(top = 25.dp)
                          .width(68.dp).height(68.dp)
                      ,
                      contentPadding = PaddingValues(0.dp)
                  ) { Icon(
                      imageVector = Icons.Default.Pix,
                      contentDescription = "Home",
                      tint = Azul,
                      modifier = Modifier.size(42.dp)
                  )}
                  Text(
                      text = "Transferência",
                      fontWeight = FontWeight(600),
                      color = Azul,
                      fontSize = 15.sp,
                      modifier = Modifier.padding(top = 5.dp)
                  )
              }
               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                   Button(
                       onClick = {},
                       colors = ButtonDefaults.buttonColors(containerColor = Azul.copy(alpha = 0.2f)),

                       modifier = Modifier.padding(top = 25.dp)
                           .width(68.dp).height(68.dp)
                       ,
                       contentPadding = PaddingValues(0.dp)
                   ) { Icon(
                       imageVector = Icons.Default.Wallet,
                       contentDescription = "Home",
                       tint = Azul,
                       modifier = Modifier.size(42.dp)
                   )}
                   Text(
                       text = "Receber",
                       fontWeight = FontWeight(600),
                       color = Azul,
                       fontSize = 15.sp,
                       modifier = Modifier.padding(top = 5.dp)
                   )
               }
           }
            Column(
                modifier = Modifier.padding(start = 10.dp, end =10.dp, top = 20.dp)
            ) {
                Text(
                    text = "Últimas transações",
                    fontWeight = FontWeight(500),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )

                LazyColumn (
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(20) { teste ->
                        Column(
                            modifier = Modifier.fillMaxWidth().height(70.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(color = Azul.copy(alpha = 0.2F))
                                .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)

                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Transferência recebida",
                                    fontWeight = FontWeight(440),
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    text = "Hoje",
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Eduardo Santana",

                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(top = 5.dp).alpha(0.8F)
                                )
                                Text(
                                    text = "+ 150,20",
                                    color = Verde,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(500),
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }

                    }}
            }
        }

    }

}
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Preview() {

    val navController = rememberNavController()

    AetherBankAppeduardoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            Tela(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )

        }
    }
}