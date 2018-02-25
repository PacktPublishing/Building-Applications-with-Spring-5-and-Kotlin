package com.journaler

import com.journaler.api.ApiApplication
import com.journaler.api.data.NoteDTO
import com.journaler.api.service.NoteService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [ApiApplication::class])
class NoteTest {

    @Autowired
    private lateinit var service: NoteService

    private val notes = mutableListOf<NoteDTO>()

    @Before
    fun prepare() {
        Assert.assertNotNull(service)
        // We will prepare 10 Note instances to be inserted:
        (0..10).mapTo(notes) {
            NoteDTO(
                    "Stub note title: $it",
                    "Stub note message: $it"
            )
        }
    }

    @Test
    fun crud() {
        // Test Note entity CRUD operations.
        cleanup()   // We will empty database before run test.
        insert()    // We will insert all prepared Note instances into database.
        update()    // We will update each Note.
        select()    // We will verify that saved Note instances are valid.
        delete()    // We will remove all Note instances from database.
    }

    fun cleanup() {
        service.getNotes().forEach { note ->
            service.deleteNote(note.id)
        }
    }

    fun insert() {
        notes.forEach { note ->
            val result = service.insertNote(note)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            note.id = result.id
        }
    }

    fun update() {
        notes.forEach { note ->
            note.title = "updated"
            note.message = "updated"
            val result = service.updateNote(note)
            Assert.assertNotNull(result)
            Assert.assertNotNull(result.id)
            Assert.assertFalse(result.id.isEmpty())
            Assert.assertEquals("updated", result.title)
            Assert.assertEquals("updated", result.message)
        }
    }

    fun delete() {
        notes.forEach { note ->
            println("Removing note with id: ${note.id}")
            service.deleteNote(note.id)
        }
    }

    fun select() {
        val result = service.getNotes()
        result.forEach { note ->
            Assert.assertNotNull(note)
            Assert.assertNotNull(note.id)
            Assert.assertFalse(note.id.isEmpty())
            Assert.assertEquals("updated", note.title)
            Assert.assertEquals("updated", note.message)
        }
    }
}