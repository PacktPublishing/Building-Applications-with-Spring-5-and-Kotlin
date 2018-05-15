package com.journaler.api.service

import com.journaler.api.data.Todo
import com.journaler.api.data.TodoDTO
import com.journaler.api.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service("Todo service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository

    fun getTodos(): Iterable<TodoDTO> = repository.findAll().map { it -> TodoDTO(it) }

    fun insertTodo(todo: TodoDTO) = TodoDTO(
            repository.save(
                    Todo(
                            title = todo.title,
                            message = todo.message,
                            location = todo.location,
                            schedule = todo.schedule

                    )
            )
    )

    fun deleteTodo(id: String) = repository.deleteById(id)

    fun updateTodo(todoDto: TodoDTO): TodoDTO {
        val todo = repository.findById(todoDto.id).get()
        todo.title = todoDto.title
        todo.message = todoDto.message
        todo.location = todoDto.location
        todo.schedule = todoDto.schedule
        todo.modified = Date()
        return TodoDTO(repository.save(todo))
    }

    fun getScheduledLaterThan(date: Date?): Iterable<TodoDTO> {
        date?.let {
            return repository.findScheduledLaterThan(date.time).map { it -> TodoDTO(it) }
        }
        return listOf()
    }

}