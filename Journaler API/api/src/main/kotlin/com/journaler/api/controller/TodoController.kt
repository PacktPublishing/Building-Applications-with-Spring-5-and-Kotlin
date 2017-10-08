package com.journaler.api.controller

import com.journaler.api.data.Todo
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
class TodoController {

    /**
     * Get todos.
     */
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos(): List<Todo> {
        return listOf(
                Todo(
                        UUID.randomUUID().toString(),
                        "My first todo",
                        "This is a message for the 1st todo.",
                        System.currentTimeMillis()
                ),
                Todo(
                        UUID.randomUUID().toString(),
                        "My second todo",
                        "This is a message for the 2nd todo.",
                        System.currentTimeMillis()
                )
        )
    }

    /**
     * Insert item.
     * It consumes JSON, that is: request body Todo.
     */
    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(
            @RequestBody todo: Todo
    ): Todo {
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    /**
     * Remove item by Id.
     * We introduced path variable for Id to pass.
     */
    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteTodo(@PathVariable(name = "id") id: String): Boolean {
        println("Removing: $id")
        return true
    }

    /**
     * Update item.
     * It consumes JSON, that is: request body Todo.
     * As result it returns updated Todo.
     */
    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateTodo(@RequestBody todo: Todo): Todo {
        todo.title += " [ updated ]"
        todo.message += " [ updated ]"
        todo.schedule = System.currentTimeMillis()
        return todo
    }

}