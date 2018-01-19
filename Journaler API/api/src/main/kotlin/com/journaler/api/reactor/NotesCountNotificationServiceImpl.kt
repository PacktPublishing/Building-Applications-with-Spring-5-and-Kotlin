package com.journaler.api.reactor

import com.journaler.api.mail.MailMessage
import com.journaler.api.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotesCountNotificationServiceImpl : NotesCountNotificationService {

    @Autowired
    private lateinit var mailService: MailService

    override fun notify(notification: NotesCountNotification) {
        val to = "go.reactive.with.spring@mailinator.com"
        val subject = "Notes count notification"
        val text = "Notes reached ${notification.notesCount} count."
        val message = MailMessage(to, subject, text)
        mailService.sendMessage(message)
    }

}