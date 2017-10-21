package com.journaler.api.controller

import com.journaler.api.data.NoteDTO
import com.journaler.api.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NoteController {

    @Autowired
    private lateinit var service: NoteService

    @GetMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() = service.getNotes()

    @PutMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: NoteDTO
    ) = service.insertNote(note)

    @DeleteMapping(
            value = "/{id}",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteNote(
            @PathVariable(name = "id") id: String
    ) = service.deleteNote(id)

    @PostMapping(
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateNote(
            @RequestBody note: NoteDTO
    ): NoteDTO = service.updateNote(note)

    @PostMapping(
            value = "/by_title",
            produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodosLaterThan(
            @RequestBody payload: NoteFindByTitleRequest
    ): Iterable<NoteDTO> = service.findByTitle(payload.title)

}