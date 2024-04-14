package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TriviaVMFactory(private val romeDAO: TriviaDAO?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaMainVM::class.java)) {
            return TriviaMainVM(romeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}