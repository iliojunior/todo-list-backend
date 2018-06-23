package com.ilioadriano.todolist.todo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Todo(
        @Id
        @GeneratedValue
        val id: Int = 0,
        val title: String,
        val description: String,
        var situation: SituationTodo = SituationTodo.NEW
) {
    private constructor(): this(title = "", description = "")
}

