package com.example.trivia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        val romeDB = TriviaDatabase.getDatabase(this)
        val romeDAO = romeDB.triviaDAO()

        val viewModelFactory = TriviaGameVMFactory(romeDAO)
        val viewModel by viewModels<TriviaGameVM> { viewModelFactory }
    }
}