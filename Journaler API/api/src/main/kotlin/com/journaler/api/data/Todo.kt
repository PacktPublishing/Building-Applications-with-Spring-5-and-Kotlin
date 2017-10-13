package com.journaler.api.data

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "todo")
data class Todo(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(columnDefinition = "varchar(36)")
        var id: String = "",
        var title: String,
        var message: String,
        var schedule: Long,
        var location: String = ""
) {

    /**
     * Hibernate tries creates a bean via reflection.
     * It does the object creation by calling the no-arg constructor.
     * Then it uses the setter methods to set the properties.
     *
     * If there is no default constructor, the following excpetion happens:
     * org.hibernate.InstantiationException: No default constructor for entity...
     */
    constructor() : this(
            "", "", "", -1, ""
    )

}