package com.journaler.api.reactor

import com.journaler.api.mail.MailMessage
import com.journaler.api.mail.MailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodosCountNotificationServiceImpl : TodosCountNotificationService {

    @Autowired
    private lateinit var mailService: MailService

    override fun notify(notification: TodosCountNotification) {
        val to = "go.reactive.with.spring@mailinator.com"
        val subject = "Notes count notification"
        val text = "Todos reached ${notification.todosCount} count."
        val message = MailMessage(to, subject, text)
        mailService.sendMessage(message)
    }

}