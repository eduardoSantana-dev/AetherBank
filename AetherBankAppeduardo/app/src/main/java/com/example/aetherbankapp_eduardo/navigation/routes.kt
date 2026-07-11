package com.example.aetherbankapp_eduardo.navigation

sealed class Routes(val route: String) {


    object Home : Routes("home")
    object PagarChave : Routes("pagarChave")

    object Pagamento : Routes("pagamento")
    object Cadastro : Routes("cadastro")
    object Login : Routes("login")


}