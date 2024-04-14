package com.example.trivia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TriviaMainVM(private val romeDAO: TriviaDAO?): ViewModel() {
    val quesText = MutableLiveData<String>()

    fun updateText() {
        viewModelScope.launch {
            quesText.postValue(romeDAO?.getQuesText(1))
        }
    }

}
