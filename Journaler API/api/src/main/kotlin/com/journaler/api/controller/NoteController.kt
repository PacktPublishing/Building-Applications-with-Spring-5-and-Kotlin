package com.journaler.api.controller

import com.journaler.api.data.Note
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/notes")
class NoteController {

    @GetMapping(
            value = "/obtain",
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

}