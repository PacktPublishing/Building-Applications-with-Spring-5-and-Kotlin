package com.journaler.api.mail

import org.hibernate.validator.constraints.Email
import org.jetbrains.annotations.NotNull

data class MailMessage(
        @Email
        @NotNull
        val to: String,
        val subject: String,
        val text: String
)