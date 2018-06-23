package com.ilioadriano.todolist.todo

import com.ilioadriano.todolist.todo.dto.TodoPostDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(
        @Autowired
        val todoService: TodoService
) {
    @GetMapping
    fun index() = todoService.findAllTodos()

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int) = todoService.findTodo(id)

    @PostMapping
    fun store(@RequestBody postDto: TodoPostDTO): ResponseEntity<Todo> {
        val todoCreated = todoService.createTodo(postDto.title, postDto.description)
        return ResponseEntity.ok(todoCreated)
    }

    @PatchMapping("/{id}/doing")
    fun moveToDoing(@PathVariable id: Int): HttpEntity<*> {
        val newSituation = SituationTodo.DOING
        return moveTodoToSituation(id, newSituation)
    }

    @PatchMapping("/{id}/done")
    fun moveToDone(@PathVariable id:Int): HttpEntity<*> {
        val doneSituation = SituationTodo.DONE
        return moveTodoToSituation(id, doneSituation)
    }

    private fun moveTodoToSituation(id:Int, newSituation:SituationTodo): HttpEntity<*> {
        todoService.moveToSituation(id, newSituation)
        return ResponseEntity.EMPTY
    }
}