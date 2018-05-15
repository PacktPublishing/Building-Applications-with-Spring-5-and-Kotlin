package com.journaler.api.mail

interface MailService {

    fun sendMessage(message: MailMessage)

}