package com.journaler.api.service

import com.journaler.api.data.Note
import com.journaler.api.data.NoteDTO
import com.journaler.api.reactor.NotesCountNotification
import com.journaler.api.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.bus.Event
import reactor.bus.EventBus
import java.util.*

@Service
class NoteService {

    @Autowired
    lateinit var repository: NoteRepository

    @Autowired
    private lateinit var eventBus: EventBus

    fun getNotes(): Iterable<NoteDTO> = repository.findAll().map { it -> NoteDTO(it) }

    fun insertNote(note: NoteDTO): NoteDTO {
        val result = NoteDTO(
                repository.save(
                        Note(
                                title = note.title,
                                message = note.message,
                                location = note.location
                        )
                )
        )
        val count = getNotes().count()
        if (count > 10) {
            val notification = NotesCountNotification(count)
            eventBus.notify("notesCountNotificationConsumer", Event.wrap(notification))
        }
        return result
    }

    fun deleteNote(id: String) = repository.delete(id)

    fun updateNote(noteDto: NoteDTO): NoteDTO {
        var note = repository.findOne(noteDto.id)
        note.title = noteDto.title
        note.message = noteDto.message
        note.location = noteDto.location
        note.modified = Date()
        note = repository.save(note)
        return NoteDTO(note)
    }

    fun findByTitle(title: String): Iterable<NoteDTO> {
        return repository.findByTitle(title).map { it -> NoteDTO(it) }
    }

}