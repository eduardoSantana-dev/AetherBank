package com.example.aetherbankapp_eduardo.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.LoginResponse
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.retrofit.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import com.example.aetherbankapp_eduardo.dataStore.DataStoreConfig
import kotlinx.coroutines.flow.first

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository()
    private val dataStoreConfig = DataStoreConfig(application)

    var logado by mutableStateOf(false)
        private set
    var email by mutableStateOf("edu")
        private set
    fun emailChange(valor : String){
        email = valor
    }
    var senha by mutableStateOf("123")
        private set
    fun senhaChange(valor : String){
        senha = valor
    }
    var alertT by mutableStateOf("MSG ALERT")
        private set
    var mostrarErro by mutableStateOf(false)
        private set

    var loading by mutableStateOf(true)
        private set
    fun fechar(){
        mostrarErro = false
    }

    init {
        viewModelScope.launch {
            Log.d("teste", "validando...")

            try {
                val token = dataStoreConfig.getToken().first()?:""
                Log.d("teste", "tonken: $token")
                if(token.isNotEmpty()){
                    Log.d("teste", "validando...2")

                    val resposta =repository.validarToken("Bearer $token")
                    Log.d("teste", "resposta: $resposta")

                    if(resposta.isSuccessful){
                        Log.d("teste", "logado")

                        logado = true
                    }
                }



            }catch (erro:Exception){

                Log.e("LoginViewModel","Erro ao validar token",erro)
            }
            loading = false
        }
    }
    fun login(){
        loading = true
        viewModelScope.launch {
            val resposta: Response<LoginResponse> = repository.login(
                LoginRequest(email,senha)
            )
            if(resposta.isSuccessful){
                val token: String = resposta.body()?.token?:""
                dataStoreConfig.salvarToken(token)
                logado = true
            }else{
                loading = false

                alertT ="E-mail ou senha inválidos."
                mostrarErro = true
            }
        }
    }
}