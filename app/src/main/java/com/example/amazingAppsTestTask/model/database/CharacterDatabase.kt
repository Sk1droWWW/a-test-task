package com.example.amazingAppsTestTask.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Database class with a singleton INSTANCE object.
 */
@Database(
    entities = [
        Character::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase
    : RoomDatabase() {
        abstract fun itemDao(): CharacterDao

        companion object {
            @Volatile
            private var INSTANCE: CharacterDatabase? = null

            fun getDatabase(context: Context): CharacterDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CharacterDatabase::class.java,
                        "habit_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }
}