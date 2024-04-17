package com.example.trivia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class TriviaAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TriviaAdapter.ViewHolder>() {

    private var dataSet: List<String?> = listOf()

    interface OnItemClickListener {
        fun onItemClick(position: Int, text: String)
    }

    class ViewHolder(view: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val text = textView.text.toString()
            listener.onItemClick(adapterPosition, text)
        }

        fun getText(): String {
            return textView.text.toString()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size

    fun setData(newData: List<String?>) {
        dataSet = newData
        notifyDataSetChanged()
    }

}
