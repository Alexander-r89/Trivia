package com.example.trivia

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class GameActivity : AppCompatActivity() {

    private lateinit var romeDB: TriviaDatabase
    private lateinit var romeDAO: TriviaDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        lifecycleScope.launch {
            initDatabaseAndDao()
            setupUI()
        }

    }

    fun runTrivia(viewModel: TriviaGameVM, button1: Button, button2: Button, button3: Button, button4: Button, ansCompare: String?) {
        lifecycleScope.launch {
            val quesCount = countSet(viewModel)
            var answerCount = 0
            var ansCorrect = 0
            val textAnsCorr = findViewById<TextView>(R.id.correctView)
            val textQuesCount = findViewById<TextView>(R.id.totalView)
            textQuesCount.text = quesCount.toString()
            var quesList:MutableList<Int> = viewModel.makeList()
            if (quesCount != null) {
                if (quesCount > 0) {
                    populate(viewModel, quesList, button1, button2, button3, button4)
                    answerCount += 1

                    button1.setOnClickListener {
                        lifecycleScope.launch {
                        if (button1.text == viewModel.ansCompare.value && answerCount <= quesCount) {
                            ansCorrect += 1
                            answerCount += 1
                            textAnsCorr.text = ansCorrect.toString()
                            button1.setBackgroundColor(Color.GREEN)
                            button1.setTextColor(Color.BLACK)
                        } else {
                            button1.setBackgroundColor(Color.RED)
                            button1.setTextColor(Color.BLACK)
                            if (button2.text == viewModel.ansCompare.value) {
                                button2.setBackgroundColor(Color.GREEN)
                                button2.setTextColor(Color.BLACK)
                            }
                            if (button3.text == viewModel.ansCompare.value) {
                                button3.setBackgroundColor(Color.GREEN)
                                button3.setTextColor(Color.BLACK)
                            }
                            if (button4.text == viewModel.ansCompare.value) {
                                button4.setBackgroundColor(Color.GREEN)
                                button4.setTextColor(Color.BLACK)
                            }
                        }
                        if (answerCount <= quesCount) {
                            Handler().postDelayed({
                                lifecycleScope.launch {
                                populate(viewModel, quesList, button1, button2, button3, button4)
                                    }
                            }, 3000)

                            }
                        }
                    }
                    button2.setOnClickListener {
                        lifecycleScope.launch {
                        if (button2.text == viewModel.ansCompare.value && answerCount <= quesCount) {
                            ansCorrect += 1
                            answerCount += 1
                            textAnsCorr.text = ansCorrect.toString()
                            button2.setBackgroundColor(Color.GREEN)
                            button2.setTextColor(Color.BLACK)
                        } else {
                            button2.setBackgroundColor(Color.RED)
                            button2.setTextColor(Color.BLACK)
                            if (button1.text == viewModel.ansCompare.value) {
                                button1.setBackgroundColor(Color.GREEN)
                                button1.setTextColor(Color.BLACK)
                            }
                            if (button3.text == viewModel.ansCompare.value) {
                                button3.setBackgroundColor(Color.GREEN)
                                button3.setTextColor(Color.BLACK)
                            }
                            if (button4.text == viewModel.ansCompare.value) {
                                button4.setBackgroundColor(Color.GREEN)
                                button4.setTextColor(Color.BLACK)
                            }
                        }
                        if (answerCount <= quesCount) {
                            Handler().postDelayed({
                                lifecycleScope.launch {
                                    populate(viewModel, quesList, button1, button2, button3, button4)
                                }
                            }, 3000)
                            }

                        }
                    }
                    button3.setOnClickListener {
                        lifecycleScope.launch {
                        if (button3.text == viewModel.ansCompare.value && answerCount <= quesCount) {
                            ansCorrect += 1
                            answerCount += 1
                            textAnsCorr.text = ansCorrect.toString()
                            button3.setBackgroundColor(Color.GREEN)
                            button3.setTextColor(Color.BLACK)
                        } else {
                            button3.setBackgroundColor(Color.RED)
                            button3.setTextColor(Color.BLACK)
                            if (button1.text == viewModel.ansCompare.value) {
                                button1.setBackgroundColor(Color.GREEN)
                                button1.setTextColor(Color.BLACK)
                            }
                            if (button2.text == viewModel.ansCompare.value) {
                                button2.setBackgroundColor(Color.GREEN)
                                button2.setTextColor(Color.BLACK)
                            }
                            if (button4.text == viewModel.ansCompare.value) {
                                button4.setBackgroundColor(Color.GREEN)
                                button4.setTextColor(Color.BLACK)
                            }
                        }
                        if (answerCount <= quesCount) {
                            Handler().postDelayed({
                                lifecycleScope.launch {
                                    populate(viewModel, quesList, button1, button2, button3, button4)
                                }
                            }, 3000)
                            }

                        }
                    }
                    button4.setOnClickListener {
                        lifecycleScope.launch {
                        if (button4.text == viewModel.ansCompare.value && answerCount <= quesCount) {
                            ansCorrect += 1
                            answerCount += 1
                            textAnsCorr.text = ansCorrect.toString()
                            button4.setBackgroundColor(Color.GREEN)
                            button4.setTextColor(Color.BLACK)
                        } else {
                            button4.setBackgroundColor(Color.RED)
                            button4.setTextColor(Color.BLACK)
                            if (button1.text == viewModel.ansCompare.value) {
                                button1.setBackgroundColor(Color.GREEN)
                                button1.setTextColor(Color.BLACK)
                            }
                            if (button3.text == viewModel.ansCompare.value) {
                                button3.setBackgroundColor(Color.GREEN)
                                button3.setTextColor(Color.BLACK)
                            }
                            if (button2.text == viewModel.ansCompare.value) {
                                button2.setBackgroundColor(Color.GREEN)
                                button2.setTextColor(Color.BLACK)
                            }
                        }
                        if (answerCount <= quesCount) {
                            Handler().postDelayed({
                                lifecycleScope.launch {
                                    populate(viewModel, quesList, button1, button2, button3, button4)
                                }
                            }, 3000)
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

    suspend fun populate(viewModel: TriviaGameVM, quesList:MutableList<Int>, button1: Button, button2: Button, button3: Button, button4: Button){

        button1.setBackgroundColor(Color.parseColor("#FF3700B3"))
        button2.setBackgroundColor(Color.parseColor("#FF3700B3"))
        button3.setBackgroundColor(Color.parseColor("#FF3700B3"))
        button4.setBackgroundColor(Color.parseColor("#FF3700B3"))
        button1.setTextColor(Color.WHITE)
        button2.setTextColor(Color.WHITE)
        button3.setTextColor(Color.WHITE)
        button4.setTextColor(Color.WHITE)
            val chosenQues = quesList.removeLast()
            viewModel.chooseQuestion(chosenQues)
    }

    suspend fun countSet(viewModel: TriviaGameVM): Int? = withContext(Dispatchers.Default) {

        return@withContext viewModel.getCount()

    }



private suspend fun initDatabaseAndDao() {
    withContext(Dispatchers.IO) {
        romeDB = TriviaDatabase.getDatabase(this@GameActivity)
        romeDAO = romeDB.triviaDAO()!!
    }
}

    private fun setupUI() {
        val text1 = findViewById<TextView>(R.id.QuesView)
        val button1 = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        var ansCompare: String? = ""

        val viewModel = ViewModelProvider(this, TriviaGameVMFactory(romeDAO)).get(TriviaGameVM::class.java)


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

        viewModel.ansCompare.observe(this, Observer { newAnsCompare ->
            ansCompare = newAnsCompare
        })

        runTrivia(viewModel, button1, button2, button3, button4, ansCompare)
    }
}