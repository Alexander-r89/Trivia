package com.example.trivia

import android.os.Bundle
import android.widget.ToggleButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class AddRemoveActivity : AppCompatActivity(), TriviaAdapter.OnItemClickListener {
    private lateinit var viewModel: TriviaARVM
    private lateinit var updateSwitch: ToggleButton
    private lateinit var deleteSwitch: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_remove)

        updateSwitch = findViewById(R.id.toggleUpdateButton)
        deleteSwitch = findViewById(R.id.toggleDeleteButton)

        val romeDB = TriviaDatabase.getDatabase(this)
        val romeDAO = romeDB.triviaDAO()
        val viewModelFactory = TriviaARVMFactory(romeDAO)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TriviaARVM::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        val customAdapter = TriviaAdapter(this)

        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.quesList.observe(this, Observer { newAllQues ->
            customAdapter.setData(newAllQues)
        })

    }

    override fun onItemClick(position: Int, text: String) {
        lifecycleScope.launch {
            val deleteToggle = deleteSwitch.isChecked
            val updateToggle = updateSwitch.isChecked

            if (deleteToggle && !updateToggle) {
                viewModel.deleteItem(text)
            }
            if (!deleteToggle && updateToggle) {

            }
        }
    }

}