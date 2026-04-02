package com.kiko.kareerai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kiko.kareerai.data.local.database.KareerAIDatabase


class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val db = KareerAIDatabase.getDatabase(application)

}
