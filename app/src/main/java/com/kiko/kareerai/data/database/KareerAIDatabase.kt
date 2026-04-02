package com.kiko.kareerai.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiko.kareerai.data.dao.UsuarioDao
import com.kiko.kareerai.data.entity.Usuario


class KareerAIDatabase {
}

@Database(
    entities = [Usuario::class],
    version = 2, // Aumentado de 1 para 2 devido às mudanças no Sos e Vacina
    exportSchema = false
)
abstract class KareerAIBD : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao


    companion object {
        @Volatile
        private var INSTANCE: KareerAIBD? = null

        fun getDatabase(context: Context): KareerAIBD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KareerAIBD::class.java,
                    "bdKareerAI"
                )
                    .fallbackToDestructiveMigration() // Isso evita o erro de schema limpando o banco antigo
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
