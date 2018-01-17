package com.journaler.api.reactor

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "notes_notifications")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class NotesCountNotification(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var notesCount: Long = 0
)