package com.ilioadriano.todolist.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TodoService(
        @Autowired
        val todoRepository: TodoRepository
) {
    fun findAllTodos() = todoRepository.findAll()

    fun createTodo(title: String, description: String): Todo {
        val newTodo = Todo(
                title = title,
                description = description
        )

        todoRepository.save(newTodo)
        return newTodo
    }

    fun moveToSituation(idTodo:Int, newSituation: SituationTodo) {
        val todoModel = findTodo(idTodo)
        todoModel.situation = newSituation
        todoRepository.save(todoModel)
    }

    fun findTodo(idTodo: Int) = todoRepository.findById(idTodo).get()
}
