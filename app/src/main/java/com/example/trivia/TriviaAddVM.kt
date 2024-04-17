package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TriviaAddVM (private val romeDAO: TriviaDAO?): ViewModel() {
    fun insert(questionText: String, answerText: String, wrong1: String, wrong2: String, wrong3: String) {
        viewModelScope.launch {
            val trivia = TriviaEntity(
                questionText = questionText,
                answerText = answerText,
                wrong1 = wrong1,
                wrong2 = wrong2,
                wrong3 = wrong3
            )
            romeDAO?.insert(trivia)
        }
    }
}