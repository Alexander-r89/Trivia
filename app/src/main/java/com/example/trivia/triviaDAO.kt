package com.example.trivia

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TriviaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(triviaEntity: TriviaEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(triviaEntity: TriviaEntity): Int

    @Delete
    suspend fun delete(triviaEntity: TriviaEntity): Int

    @Query("SELECT * FROM trivia WHERE id = :id")
    suspend fun getById(id: Int): TriviaEntity

    @Query("SELECT question_Text FROM trivia WHERE id = :id")
    suspend fun getQuesText(id: Int): String
}