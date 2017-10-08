package com.journaler.api.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notes")
@EnableAutoConfiguration
class NoteController {

    @GetMapping(
            value = "/obtain",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() : String {
        return "Work in progress."
    }

}