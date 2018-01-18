package com.journaler.api.service.reactor

import com.journaler.api.mail.MailMessage
import com.journaler.api.mail.MailService
import com.journaler.api.reactor.TodosCountNotification
import org.springframework.beans.factory.annotation.Autowired

class TodosCountNotificationServiceImpl : TodosCountNotificationService {

    @Autowired
    private lateinit var mailService: MailService

    override fun notify(notification: TodosCountNotification) {
        val to = "your_email@example.com"
        val subject = "Notes count notification"
        val text = "Todos reached ${notification.todosCount} count."
        val message = MailMessage(to, subject, text)
        mailService.sendMessage(message)
    }

}