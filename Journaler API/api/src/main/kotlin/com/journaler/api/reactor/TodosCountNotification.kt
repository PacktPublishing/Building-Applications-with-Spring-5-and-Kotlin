package com.journaler.api.reactor

import com.fasterxml.jackson.annotation.JsonInclude
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "todos_notifications")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TodosCountNotification(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var todosCount: Long = 0
)