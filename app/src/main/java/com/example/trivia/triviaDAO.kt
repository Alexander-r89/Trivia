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

    @Delete
    suspend fun delete(triviaEntity: TriviaEntity): Int

    @Query("DELETE FROM trivia WHERE question_Text = :quesText")
    suspend fun deleteByText(quesText: String?)

    @Query("SELECT id FROM trivia")
    fun getById(): MutableList<Int>

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

    @Query("SELECT answer_Correct FROM trivia WHERE question_Text = :quesText")
    suspend fun ansByQues(quesText: String?): String

    @Query("SELECT answer_Wrong1 FROM trivia WHERE question_Text = :quesText")
    suspend fun wrng1ByQues(quesText: String?): String

    @Query("SELECT answer_Wrong2 FROM trivia WHERE question_Text = :quesText")
    suspend fun wrng2ByQues(quesText: String?): String

    @Query("SELECT answer_Wrong3 FROM trivia WHERE question_Text = :quesText")
    suspend fun wrng3ByQues(quesText: String?): String

    @Query("UPDATE trivia SET question_Text = :question WHERE question_Text = :quesText")
    suspend fun updateQuestion(quesText: String?, question: String)

    @Query("UPDATE trivia SET answer_Correct = :answer WHERE question_Text = :quesText")
    suspend fun updateAnswer(quesText: String?, answer: String)

    @Query("UPDATE trivia SET answer_Wrong1 = :wrong1 WHERE question_Text = :quesText")
    suspend fun updateWrong1(quesText: String?, wrong1: String)

    @Query("UPDATE trivia SET answer_Wrong2 = :wrong2 WHERE question_Text = :quesText")
    suspend fun updateWrong2(quesText: String?, wrong2: String)

    @Query("UPDATE trivia SET answer_Wrong3 = :wrong3 WHERE question_Text = :quesText")
    suspend fun updateWrong3(quesText: String?, wrong3: String)
}