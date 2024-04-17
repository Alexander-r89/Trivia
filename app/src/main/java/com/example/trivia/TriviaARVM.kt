package com.example.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TriviaARVM(private val romeDAO: TriviaDAO?): ViewModel() {
    val quesList: LiveData<List<String?>> = romeDAO!!.getAllQuesText()


    suspend fun deleteItem(item: String?) {
        romeDAO!!.deleteByText(item)
    }
}