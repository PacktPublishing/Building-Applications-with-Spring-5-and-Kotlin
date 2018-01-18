package com.journaler.api.reactor

import com.journaler.api.service.reactor.TodosCountNotificationService
import org.springframework.beans.factory.annotation.Autowired
import reactor.bus.Event

class TodosCountNotificationConsumer : NotificationConsumer<TodosCountNotification> {

    @Autowired
    private lateinit var service: TodosCountNotificationService

    override fun accept(e: Event<TodosCountNotification>?) {
        val data = e?.data
        data?.let {
            service.notify(data)
        }
    }

}