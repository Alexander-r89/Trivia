package com.example.trivia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 = findViewById<TextView>(R.id.textViewTest)
        val button1 = findViewById<Button>(R.id.buttonTest)
        val romeDB = TriviaDatabase.getDatabase(this)
        val romeDAO = romeDB.triviaDAO()

        val viewModelFactory = TriviaVMFactory(romeDAO)
        val viewModel by viewModels<TriviaMainVM> { viewModelFactory }


        button1.setOnClickListener {
            Log.d("MainActivity", "button1 clicked")
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }

    }
}

