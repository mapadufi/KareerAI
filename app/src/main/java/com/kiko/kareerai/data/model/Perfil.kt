package com.kiko.kareerai.data.model

data class Perfil(

    val cargo: String,

    val nivel: String,

    val tecnologias: List<String> = emptyList(),

    val localizacao: String = "",

    val remoto: Boolean = true,

    val palavrasChave: List<String> = emptyList()
)