package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TriviaGameVMFactory(private val romeDAO: TriviaDAO?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaGameVM::class.java)) {
            return TriviaGameVM(romeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}