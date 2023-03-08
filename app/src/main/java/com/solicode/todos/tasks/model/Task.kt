package com.solicode.todos.tasks.model

import com.solicode.todos.tasks.enum.STATUS

data class Task(var title: String, val status: STATUS) {
}