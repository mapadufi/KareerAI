package com.kiko.kareerai.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiko.kareerai.data.local.dao.CurriculoDao
import com.kiko.kareerai.data.local.dao.JobDao
import com.kiko.kareerai.data.local.dao.UsuarioDao
import com.kiko.kareerai.data.local.entity.Curriculo
import com.kiko.kareerai.data.local.entity.Job
import com.kiko.kareerai.data.local.entity.Usuario

@Database(
    entities = [
        Usuario::class,
        Job::class,
        Curriculo::class
    ],
    version = 2,
    exportSchema = false
)
abstract class KareerAIDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun jobDao(): JobDao
    abstract fun curriculoDao(): CurriculoDao

    companion object {

        @Volatile
        private var INSTANCE: KareerAIDatabase? = null

        fun getDatabase(context: Context): KareerAIDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KareerAIDatabase::class.java,
                    "kareerai_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}