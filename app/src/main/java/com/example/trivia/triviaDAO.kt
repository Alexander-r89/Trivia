package com.example.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    @Query("DELETE FROM trivia WHERE question_Text = :quesText")
    suspend fun deleteByText(quesText: String?)

    @Query("SELECT id FROM trivia")
    suspend fun getById(): MutableList<Int>

    @Query("SELECT question_Text FROM trivia WHERE id = :id")
    fun getQuesText(id: Int?): String

    @Query("SELECT question_Text FROM trivia")
    fun getAllQuesText(): LiveData<List<String?>>

    @Query("SELECT answer_Correct FROM trivia WHERE id = :id")
    suspend fun getCorrectText(id: Int?): String

    @Query("SELECT answer_Wrong1 FROM trivia WHERE id = :id")
    suspend fun getWrong1Text(id: Int?): String

    @Query("SELECT answer_Wrong2 FROM trivia WHERE id = :id")
    suspend fun getWrong2Text(id: Int?): String

    @Query("SELECT answer_Wrong3 FROM trivia WHERE id = :id")
    suspend fun getWrong3Text(id: Int?): String

    @Query("SELECT COUNT(id) FROM trivia")
    suspend fun getRowCount(): Int
}