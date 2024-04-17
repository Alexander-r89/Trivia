package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TriviaUpdateVM (private val romeDAO: TriviaDAO?): ViewModel() {
    fun update(originalQuestion: String?, questionText: String, answerText: String, wrong1: String, wrong2: String, wrong3: String) {
        viewModelScope.launch {
            romeDAO!!.updateQuestion(originalQuestion, questionText)
            romeDAO.updateAnswer(originalQuestion, answerText)
            romeDAO.updateWrong1(originalQuestion, wrong1)
            romeDAO.updateWrong2(originalQuestion, wrong2)
            romeDAO.updateWrong3(originalQuestion, wrong3)
        }
    }

    suspend fun getAns(question: String?): String {
        val ans = romeDAO!!.ansByQues(question)
        return ans
    }
    suspend fun getWrng1(question: String?): String {
        val wrng1 = romeDAO!!.wrng1ByQues(question)
        return wrng1
    }
    suspend fun getWrng2(question: String?): String {
        val wrng2 = romeDAO!!.wrng2ByQues(question)
        return wrng2
    }
    suspend fun getWrng3(question: String?): String {
        val wrng3 = romeDAO!!.wrng3ByQues(question)
        return wrng3
    }
}