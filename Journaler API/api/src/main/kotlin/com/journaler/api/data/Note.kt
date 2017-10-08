package com.journaler.api.data

data class Note(
        val id: String,
        val title: String,
        val message: String,
        val location: String = ""
)