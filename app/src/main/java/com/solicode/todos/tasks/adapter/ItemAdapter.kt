package com.solicode.todos.tasks.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.solicode.todos.MainActivity
import com.solicode.todos.R
import com.solicode.todos.tasks.data.Datasource
import com.solicode.todos.tasks.model.Task

class ItemAdapter(private val context: Context,
                  private val datasource: Datasource)
    : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.list_item_text)
        val editButton: Button = view.findViewById(R.id.edit_button)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task = datasource.tasks[position]
        holder.textView.text = task.title

        holder.deleteButton.setOnClickListener {
            datasource.tasks.removeAt(position)
            this.notifyItemRemoved(position)
            Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()
        }

        holder.editButton.setOnClickListener {
            val dialog = Dialog(context)
            val view: View = LayoutInflater.from(context).inflate(R.layout.edit_task_dialog, null)

            val updateButton: Button = view.findViewById(R.id.edit_task_button)
            val titleInput: EditText = view.findViewById(R.id.edit_task_title)

            titleInput.setText(task.title)

            updateButton.setOnClickListener {
                task.title = titleInput.text.toString()
                this.notifyItemChanged(position)
                dialog.cancel()
                Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show()
            }

            dialog.setContentView(view)
            dialog.show()
        }
    }

    override fun getItemCount(): Int = datasource.tasks.size
}