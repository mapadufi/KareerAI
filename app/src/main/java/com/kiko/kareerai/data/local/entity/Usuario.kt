package com.kiko.kareerai.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "nome_usuario")
    val nomeUsuario: String,

    @ColumnInfo(name = "senha")
    val senha: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "telefone")
    val telefone: String
)
