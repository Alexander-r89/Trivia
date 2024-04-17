package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TriviaAddVMFactory(private val romeDAO: TriviaDAO?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaAddVM::class.java)) {
            return TriviaAddVM(romeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}