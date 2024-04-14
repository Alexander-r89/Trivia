package com.example.trivia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile


@Database(entities = [TriviaEntity::class], version = 1)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDAO(): TriviaDAO?

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TriviaDatabase? = null

        fun getDatabase(context: Context): TriviaDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    TriviaDatabase::class.java,
                    "TriviaDB.db"
                ).createFromAsset("trivia2.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}