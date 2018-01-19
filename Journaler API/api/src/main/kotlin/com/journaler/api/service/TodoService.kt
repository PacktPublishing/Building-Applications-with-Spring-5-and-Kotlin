package com.journaler.api.service

import com.journaler.api.data.Todo
import com.journaler.api.data.TodoDTO
import com.journaler.api.reactor.TodosCountNotification
import com.journaler.api.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.bus.Event
import reactor.bus.EventBus
import java.util.*


@Service("Todo service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository

    @Autowired
    private lateinit var eventBus: EventBus

    fun getTodos(): Iterable<TodoDTO> = repository.findAll().map { it -> TodoDTO(it) }

    fun insertTodo(todo: TodoDTO): TodoDTO {
        val result = TodoDTO(
                repository.save(
                        Todo(
                                title = todo.title,
                                message = todo.message,
                                location = todo.location,
                                schedule = todo.schedule

                        )
                )
        )
        val count = getTodos().count()
        if (count > 10) {
            val notification = TodosCountNotification(count)
            eventBus.notify("todosCountNotificationConsumer", Event.wrap(notification))
        }
        return result
    }

    fun deleteTodo(id: String) = repository.delete(id)

    fun updateTodo(todoDto: TodoDTO): TodoDTO {
        var todo = repository.findOne(todoDto.id)
        todo.title = todoDto.title
        todo.message = todoDto.message
        todo.location = todoDto.location
        todo.schedule = todoDto.schedule
        todo.modified = Date()
        todo = repository.save(todo)
        return TodoDTO(todo)
    }

    fun getScheduledLaterThan(date: Date?): Iterable<TodoDTO> {
        date?.let {
            return repository.findScheduledLaterThan(date.time).map { it -> TodoDTO(it) }
        }
        return listOf()
    }

}