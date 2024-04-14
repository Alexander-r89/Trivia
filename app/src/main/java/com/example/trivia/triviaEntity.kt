package com.example.trivia

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "trivia")
data class TriviaEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,

    @ColumnInfo(name = "question_Text")
    val questionText: String,

    @ColumnInfo(name = "answer_Correct")
    val answerText: String,

    @ColumnInfo(name = "answer_Wrong1")
    val wrong1: String,

    @ColumnInfo(name = "answer_Wrong2")
    val wrong2: String,

    @ColumnInfo(name = "answer_Wrong3")
    val wrong3: String
)