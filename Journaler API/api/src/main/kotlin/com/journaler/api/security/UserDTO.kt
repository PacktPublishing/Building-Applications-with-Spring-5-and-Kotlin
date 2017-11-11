package com.journaler.api.security

data class UserDTO(
        var email: String,
        var password: String,
        var firstName: String,
        var lastName: String
)