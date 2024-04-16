package com.example.trivia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TriviaGameVM (private val romeDAO: TriviaDAO?): ViewModel() {
    val quesText = MutableLiveData<String?>()

    val ansText1 = MutableLiveData<String?>()
    val ansText2 = MutableLiveData<String?>()
    val ansText3 = MutableLiveData<String?>()
    val ansText4 = MutableLiveData<String?>()

    suspend fun getCount(): Int? {
        val quesCount = romeDAO?.getRowCount()
        return quesCount
    }

    suspend fun makeList(): MutableList<Int> {
        var quesList: MutableList<Int> = romeDAO!!.getById()
        quesList = quesList.shuffled().toMutableList()

        return quesList
    }


    suspend fun chooseQuestion(chosenQues: Int) {
        Log.d("GameActivity", "ChooseQuestion called")

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