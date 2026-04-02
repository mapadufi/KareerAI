package com.kiko.kareerai.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curriculo")
data class Curriculo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "usuario_id")
    val usuarioId: Long,

    @ColumnInfo(name = "texto_bruto")
    val textoBruto: String,

    @ColumnInfo(name = "cargo")
    val cargo: String,

    @ColumnInfo(name = "nivel")
    val nivel: String,

    @ColumnInfo(name = "tecnologias")
    val tecnologias: String, // Lista salva como String separada por vírgula

    @ColumnInfo(name = "data_criacao")
    val dataCriacao: Long = System.currentTimeMillis()
)
