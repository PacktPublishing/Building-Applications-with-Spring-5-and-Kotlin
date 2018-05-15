package com.journaler.api.mail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class MailServiceImpl : MailService {

    @Autowired
    lateinit var sender: JavaMailSender

    override fun sendMessage(message: MailMessage) {
        val toSend = SimpleMailMessage()
        toSend.to = arrayOf(message.to)
        toSend.subject = message.subject
        toSend.text = message.text
        sender.send(toSend)
    }

}