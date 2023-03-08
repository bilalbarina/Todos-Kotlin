package com.solicode.todos.tasks.data

import com.solicode.todos.tasks.enum.STATUS
import com.solicode.todos.tasks.model.Task

class Datasource {
    val tasks = mutableListOf<Task>()

    fun add(title: String, status: STATUS = STATUS.WAITING) {
        val task = Task(title, status)
        this.tasks.add(task)
    }

    fun delete(index: Int) {
        this.tasks.removeAt(index)
    }
}