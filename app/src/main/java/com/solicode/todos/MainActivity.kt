package com.solicode.todos

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solicode.todos.tasks.adapter.ItemAdapter
import com.solicode.todos.tasks.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datasource: Datasource = Datasource()

        val recyclerView = findViewById<RecyclerView>(R.id.list_view)
        recyclerView.adapter = ItemAdapter(this, datasource)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val createButton: Button = findViewById(R.id.add_task_button)
        createButton.setOnClickListener{
            val taskTitle: String = findViewById<EditText>(R.id.new_task_title).text.toString()
            datasource.add(taskTitle)
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()
            recyclerView.adapter?.notifyDataSetChanged()
        }

    }

}