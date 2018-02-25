package com.journaler

import com.journaler.api.ApiApplication
import com.journaler.api.data.TodoDTO
import com.journaler.api.service.TodoService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ApiApplication::class])
class TodoTest {

    @Autowired
    private lateinit var service: TodoService

    private val todos = mutableListOf<TodoDTO>()

    @Before
    fun prepare() {
        Assert.assertNotNull(service)
        // We will prepare 10 instances to be inserted:
        (0..10).mapTo(todos) {
            TodoDTO(
                    "Stub todo title: $it",
                    "Stub todo message: $it",
                    System.currentTimeMillis()
            )
        }
    }

    @Test
    fun crud() {
        // Test entity CRUD operations.
        cleanup()   // We will empty database before run test.
        insert()    // We will insert all prepared instances into database.
        update()    // We will update each item.
        select()    // We will verify that saved instances are valid.
        delete()    // We will remove all instances from database.
    }

    fun cleanup() {
        service.getTodos().forEach { todo ->
            service.deleteTodo(todo.id)
        }
    }

    fun insert() {
        todos.forEach { todo ->
            val result = service.insertTodo(todo)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            Assert.assertTrue(result.schedule > 0)
            todo.id = result.id
        }
    }

    fun update() {
        todos.forEach { todo ->
            todo.title = "updated"
            todo.message = "updated"
            val result = service.updateTodo(todo)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            Assert.assertEquals("updated", result.title)
            Assert.assertEquals("updated", result.message)
            Assert.assertTrue(result.schedule > 0)
        }
    }

    fun delete() {
        todos.forEach { todo ->
            println("Removing todo with id: ${todo.id}")
            service.deleteTodo(todo.id)
        }
    }

    fun select() {
        val result = service.getTodos()
        result.forEach { todo ->
            Assert.assertNotNull(todo)
            Assert.assertNotNull(todo.id)
            Assert.assertFalse(todo.id.isEmpty())
            Assert.assertEquals("updated", todo.title)
            Assert.assertEquals("updated", todo.message)
            Assert.assertTrue(todo.schedule > 0)
        }
    }

}