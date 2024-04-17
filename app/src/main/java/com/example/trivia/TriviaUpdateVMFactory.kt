package com.example.trivia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TriviaUpdateVMFactory(private val romeDAO: TriviaDAO?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TriviaUpdateVM::class.java)) {
            return TriviaUpdateVM(romeDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}