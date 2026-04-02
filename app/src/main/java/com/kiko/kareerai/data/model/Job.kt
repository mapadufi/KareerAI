package com.kiko.kareerai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class Job(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val company: String,
    val location: String,

    val description: String = "",

    val url: String,

    val isRemote: Boolean = false,

    val datePosted: String = "",

    // usado futuramente para IA (score de compatibilidade)
    val matchScore: Int = 0
)