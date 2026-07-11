package com.example.aetherbankapp_eduardo.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtos.LoginRequest
import com.example.aetherbankapp_eduardo.navigation.Routes
import com.example.aetherbankapp_eduardo.retrofit.Repository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val repository = Repository()
    var logado by mutableStateOf(false)
        private set
    var email by mutableStateOf("")
        private set
    fun emailChange(valor : String){
        email = valor
    }
    var senha by mutableStateOf("")
        private set
    fun senhaChange(valor : String){
        senha = valor
    }
    var alertT by mutableStateOf("MSG ALERT")
        private set
    var mostrarErro by mutableStateOf(false)
        private set

    fun fechar(){
        mostrarErro = false
    }

    fun login(){
        viewModelScope.launch {
            val reposta = repository.login(
                LoginRequest(email,senha)
            )
            if(reposta.isSuccessful){
                logado = true

            }else{
                alertT ="E-mail ou senha inválidos."
                mostrarErro = true
            }
        }
    }
}