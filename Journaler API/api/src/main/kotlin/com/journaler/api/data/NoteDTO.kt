package com.journaler.api.data

import java.util.*

data class NoteDTO(
        var title: String,
        var message: String,
        var location: String = ""
) {

    var id: String = ""
    var created: Date = Date()
    var modified: Date = Date()

    constructor(note: Note) : this(
            note.title,
            note.message,
            note.location
    ) {
        id = note.id
        created = note.created
        modified = note.modified
    }

}