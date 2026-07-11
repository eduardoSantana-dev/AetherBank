package com.example.aetherbankapp_eduardo.viewModel

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.dtos.RegistroRequest
import com.example.aetherbankapp_eduardo.retrofit.Repository
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel(){
    private val repository = Repository()

    var mostrarErro by mutableStateOf(false)
        private set

    var alertT by mutableStateOf("")
        private set
    var alertCadastrado by mutableStateOf(false)
        private set
    fun fechar(){
        mostrarErro = false
    }

    var nome by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var cpf by mutableStateOf("")
        private set
    var numero by mutableStateOf("")
        private set
    var senha by mutableStateOf("")
        private set
    var senhaConfirm by mutableStateOf("")
        private set

    fun nomeChange(valor: String){
        nome = valor
    }
    fun emailChange(valor: String){
        email = valor

    }
    fun cpfChange(valor: String){
        cpf = valor

    }
    fun numeroChange(valor: String){
        numero = valor

    }
    fun senhaChange(valor: String){
        senha = valor

    }
    fun senhaConfirmChange(valor: String){
        senhaConfirm = valor
    }



    fun cadastrar(){
        var correto by mutableStateOf(true)
        if(nome.length<3){
            alertT = "Nome muito curto"
            correto = false
            mostrarErro = true
            return
        }
        if(email.length<3 || !email.contains("@") || !email.contains(".") ){
            alertT = "Email inválido"
            correto = false
            mostrarErro = true
            return
        }
        if(cpf.length<11){
            alertT = "Cpf inválido"
            correto = false
            mostrarErro = true
            return
        }
        if(numero.length<11){
            alertT = "Número inválido"
            correto = false
            mostrarErro = true
            return
        }
        if(senha != senhaConfirm){
            alertT = "As senhas não são compativéis"
            correto = false
            mostrarErro = true
            return
        }
        if(senha.length<6){
            alertT = "Senha muito curta"
            correto = false
            mostrarErro = true
            return
        }
        if(correto){
            viewModelScope.launch {
              val resposta =   repository.registrar(RegistroRequest(
                    nome,
                    cpf,
                    email,
                    numero,
                    senha
                ))
                if(resposta.isSuccessful){
                    alertCadastrado = true
                }
                else{
                    alertT = "Erro ao realizar cadastro"
                    mostrarErro = true
                }
            }
        }else{
            mostrarErro = true
        }
    }
}