package com.journaler.api.mail

import org.hibernate.validator.constraints.Email
import org.jetbrains.annotations.NotNull

data class MailMessage(
        @Email
        @NotNull
        var to: String,
        var subject: String,
        var text: String
)