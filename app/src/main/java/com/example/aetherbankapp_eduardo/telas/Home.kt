package com.example.aetherbankapp_eduardo.telas

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.aetherbankapp_eduardo.Greeting
import com.example.aetherbankapp_eduardo.R
import com.example.aetherbankapp_eduardo.ui.theme.AetherBankAppeduardoTheme
import com.example.aetherbankapp_eduardo.ui.theme.Azul
import org.w3c.dom.Text


@Composable
fun Home(){
    AetherBankAppeduardoTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Tela(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun Tela(modifier: Modifier){
    var saldo by remember { mutableStateOf("12.200.67") }
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
                    .height(189.dp)
                    .padding(start = 10.dp,end = 10.dp)


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


        }
    }
}
@Preview
@Composable
fun Preview(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Tela(
            modifier = Modifier.padding(innerPadding)
        )
    }
}