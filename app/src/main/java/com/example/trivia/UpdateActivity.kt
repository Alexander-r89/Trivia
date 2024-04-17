package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_entry)

        var quesEntry = findViewById<EditText>(R.id.editTextQuestionText)
        quesEntry.setText(intent.getStringExtra("quesText"))


        val romeDB = TriviaDatabase.getDatabase(this)
        val romeDAO = romeDB.triviaDAO()

        val viewModelFactory = TriviaUpdateVMFactory(romeDAO)
        val viewModel by viewModels<TriviaUpdateVM> { viewModelFactory }

        val quesBox = findViewById<TextView>(R.id.textViewQues)
        val ansBox = findViewById<TextView>(R.id.textViewAnsw)
        val wrng1Box = findViewById<TextView>(R.id.textViewWrng1)
        val wring2Box = findViewById<TextView>(R.id.textViewWrng2)
        val wrng3Box = findViewById<TextView>(R.id.textViewWrng3)
        val submitButton = findViewById<Button>(R.id.buttonAddQues)

        var ansEntry = findViewById<EditText>(R.id.editTextAnswerText)
        var wrng1Entry = findViewById<EditText>(R.id.editTextWrong1)
        var wrng2Entry = findViewById<EditText>(R.id.editTextWrong2)
        var wrng3Entry = findViewById<EditText>(R.id.editTextWrong3)

        val originalQues = intent.getStringExtra("quesText")


        initValues(viewModel, originalQues, ansEntry, wrng1Entry, wrng2Entry, wrng3Entry)

        updateQues(viewModel, submitButton, originalQues, quesEntry, ansEntry, wrng1Entry, wrng2Entry, wrng3Entry)


    }

    fun initValues(viewModel: TriviaUpdateVM, quesText: String?, ansEntry: EditText, wrng1Entry: EditText, wrng2Entry: EditText, wrng3Entry: EditText) {
        lifecycleScope.launch {
            ansEntry.setText(viewModel.getAns(quesText))
            wrng1Entry.setText(viewModel.getWrng1(quesText))
            wrng2Entry.setText(viewModel.getWrng2(quesText))
            wrng3Entry.setText(viewModel.getWrng3(quesText))
        }
    }

    fun updateQues(viewModel: TriviaUpdateVM, submitButton: Button, quesText: String?, quesEntry: EditText,ansEntry: EditText, wrng1Entry: EditText, wrng2Entry: EditText, wrng3Entry: EditText) {

        submitButton.setOnClickListener {
            val ques = quesEntry.text.toString()
            val ans = ansEntry.text.toString()
            val wrng1 = wrng1Entry.text.toString()
            val wrng2 = wrng2Entry.text.toString()
            val wrng3 = wrng3Entry.text.toString()
            if (ques != "" && ans != "" && wrng1 !=  "" && wrng2 != "" && wrng3 != "") {
                lifecycleScope.launch {
                    viewModel.update(quesText, ques, ans, wrng1, wrng2, wrng3)
                    val intent = Intent(this@UpdateActivity, AddRemoveActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}