package com.journaler.api.repository

import com.journaler.api.data.Note
import org.springframework.data.repository.CrudRepository

/**
 * String is the type for ID we use.
 */
interface NoteRepository : CrudRepository<Note, String> {

    fun findByTitle(title: String): Iterable<Note>

}