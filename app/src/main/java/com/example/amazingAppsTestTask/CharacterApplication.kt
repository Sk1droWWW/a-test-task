package com.example.amazingAppsTestTask

import android.app.Application
import com.example.amazingAppsTestTask.database.CharacterDatabase

class CharacterApplication : Application() {
    val database: CharacterDatabase by lazy { CharacterDatabase.getDatabase(this) }
}