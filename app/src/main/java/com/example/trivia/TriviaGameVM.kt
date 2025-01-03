package com.example.trivia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TriviaGameVM (private val romeDAO: TriviaDAO?): ViewModel() {
    val quesText = MutableLiveData<String?>()

    val ansText1 = MutableLiveData<String?>()
    val ansText2 = MutableLiveData<String?>()
    val ansText3 = MutableLiveData<String?>()
    val ansText4 = MutableLiveData<String?>()

    val ansCompare = MutableLiveData<String?>()

    suspend fun getCount(): Int? = withContext(Dispatchers.IO) {
        val quesCount = romeDAO?.getRowCount()
        return@withContext quesCount
    }

    suspend fun makeList() = withContext(Dispatchers.IO) {
        var quesList: MutableList<Int> = romeDAO!!.getById()
        quesList = quesList.shuffled().toMutableList()
        return@withContext quesList
    }


    suspend fun chooseQuestion(chosenQues: Int) = withContext(Dispatchers.IO) {
        Log.d("GameActivity", "ChooseQuestion called")

        ansCompare.postValue(romeDAO?.getCorrectText(chosenQues))
        quesText.postValue(romeDAO?.getQuesText(chosenQues))
        val answerOrder = (1..4).shuffled()
        ansText1.postValue(answerGet(answerOrder[0], chosenQues))
        ansText2.postValue(answerGet(answerOrder[1], chosenQues))
        ansText3.postValue(answerGet(answerOrder[2], chosenQues))
        ansText4.postValue(answerGet(answerOrder[3], chosenQues))
    }

    suspend fun answerGet(i: Int, chosenQues: Int?): String? = withContext(Dispatchers.Default) {
        Log.d("GameActivity", "AnswerGet called")
        when (i) {
            1 -> return@withContext romeDAO?.getCorrectText(chosenQues)

            2 -> return@withContext romeDAO?.getWrong1Text(chosenQues)

            3 -> return@withContext romeDAO?.getWrong2Text(chosenQues)

            4 -> return@withContext romeDAO?.getWrong3Text(chosenQues)

            else -> {
                return@withContext "Something went wrong"
            }
        }
    }
}