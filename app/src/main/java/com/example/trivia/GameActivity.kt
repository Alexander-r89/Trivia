package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        val romeDB = TriviaDatabase.getDatabase(this)
        val romeDAO = romeDB.triviaDAO()

        val text1 = findViewById<TextView>(R.id.QuesView)
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        val viewModelFactory = TriviaGameVMFactory(romeDAO)
        val viewModel by viewModels<TriviaGameVM> { viewModelFactory }

        Log.d("GameActivity", "onCreate called")

        viewModel.quesText.observe(this, Observer { newQuesText ->
            text1.text = newQuesText
        })

        viewModel.ansText1.observe(this, Observer { newAnsText1 ->
            button1.text = newAnsText1
        })

        viewModel.ansText2.observe(this, Observer { newAnsText2 ->
            button2.text = newAnsText2
        })

        viewModel.ansText3.observe(this, Observer { newAnsText3 ->
            button3.text = newAnsText3
        })

        viewModel.ansText4.observe(this, Observer { newAnsText4 ->
            button4.text = newAnsText4
        })

        runTrivia(viewModel, button1, button2, button3, button4)

    }

    fun runTrivia(viewModel: TriviaGameVM, button1: Button, button2: Button, button3: Button, button4: Button) {
        lifecycleScope.launch {
            val quesCount = countSet(viewModel)
            var answerCount = 0
            var quesList:MutableList<Int> = viewModel.makeList()
            if (quesCount != null) {
                if (quesCount > 0) {
                    populate(viewModel, quesList)
                    answerCount += 1

                    button1.setOnClickListener {
                        if (answerCount < quesCount) {
                            lifecycleScope.launch {
                                populate(viewModel, quesList)
                                answerCount += 1
                            }
                        }
                    }
                    button2.setOnClickListener {
                        if (answerCount < quesCount) {
                            lifecycleScope.launch {
                                populate(viewModel, quesList)
                                answerCount += 1
                            }
                        }
                    }
                    button3.setOnClickListener {
                        if (answerCount < quesCount) {
                            lifecycleScope.launch {
                                populate(viewModel, quesList)
                                answerCount += 1
                            }
                        }
                    }
                    button4.setOnClickListener {
                        if (answerCount < quesCount) {
                            lifecycleScope.launch {
                                populate(viewModel, quesList)
                                answerCount += 1
                            }
                        }
                    }
                } else {
                    val intent = Intent(this@GameActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    suspend fun populate(viewModel: TriviaGameVM, quesList:MutableList<Int>){
        Log.d("GameActivity", quesList.toString())
            val chosenQues = quesList.removeLast()
            viewModel.chooseQuestion(chosenQues)
    }

    suspend fun countSet(viewModel: TriviaGameVM): Int? = withContext(Dispatchers.Default) {

        return@withContext viewModel.getCount()

    }

}