package com.journaler.api.service

import com.journaler.api.data.Todo
import org.springframework.stereotype.Service
import java.util.*


@Service("Todo service")
class TodoService {

    fun getTodos(): List<Todo> = listOf(
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

    fun insertTodo(todo: Todo): Todo {
        todo.id = UUID.randomUUID().toString()
        return todo
    }

    fun deleteTodo(id: String): Boolean = false

    fun updateTodo(todo: Todo): Boolean = true

}