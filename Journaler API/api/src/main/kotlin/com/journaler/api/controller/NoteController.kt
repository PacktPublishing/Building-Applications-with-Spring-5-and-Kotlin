package com.journaler.api.controller

import com.journaler.api.data.Note
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/notes")
class NoteController {

    /**
     * Get notes.
     */
    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes(): List<Note> {
        return listOf(
                Note(
                        UUID.randomUUID().toString(),
                        "My first note",
                        "This is a message for the 1st note."
                ),
                Note(
                        UUID.randomUUID().toString(),
                        "My second note",
                        "This is a message for the 2nd note."
                )
        )
    }

    /**
     * Insert note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: Note
    ): Note {
        note.id = UUID.randomUUID().toString()
        return note
    }

    /**
     * Remove note by Id.
     * We introduced path variable for Id to pass.
     */
    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteNote(@PathVariable(name = "id") id: String): Boolean {
        println("Removing: $id")
        return true
    }

    /**
     * Update item.
     * It consumes JSON, that is: request body Note.
     * As result it returns updated Note.
     */
    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateNote(@RequestBody note: Note): Note {
        note.title += " [ updated ]"
        note.message += " [ updated ]"
        return note
    }

}