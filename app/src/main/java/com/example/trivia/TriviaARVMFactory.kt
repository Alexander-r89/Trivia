package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TriviaARVMFactory(private val romeDAO: TriviaDAO?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaARVM::class.java)) {
            return TriviaARVM(romeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}