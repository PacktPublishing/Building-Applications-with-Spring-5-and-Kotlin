package com.journaler.api.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
class NoteController {

    @GetMapping(
            value = "/obtain",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() : String {
        return "Work in progress."
    }

}