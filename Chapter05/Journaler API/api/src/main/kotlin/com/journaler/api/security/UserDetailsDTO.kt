package com.journaler.api.security

import java.util.*

data class UserDetailsDTO(
        val id: String,
        var email: String,
        var firstName: String,
        var lastName: String,
        var roles: String,
        var enabled: Boolean,
        var accountNonExpired: Boolean,
        var accountNonLocked: Boolean,
        var credentialsNonExpired: Boolean,
        var created: Date,
        var modified: Date
)