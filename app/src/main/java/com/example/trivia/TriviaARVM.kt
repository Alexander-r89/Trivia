package com.example.trivia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TriviaARVM(private val romeDAO: TriviaDAO?): ViewModel() {
    val quesList = MutableLiveData<List<String?>>()

    init {
        viewModelScope.launch {
            makeList()
        }
    }

    suspend fun makeList() {
        quesList.postValue(romeDAO!!.getAllQuesText())
    }

    suspend fun deleteItem(item: String) {
        romeDAO!!.deleteByText(item)
    }
}