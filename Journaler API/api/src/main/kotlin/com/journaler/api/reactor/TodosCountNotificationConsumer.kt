package com.journaler.api.reactor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.bus.Event

@Service
class TodosCountNotificationConsumer : NotificationConsumer<TodosCountNotification> {

    @Autowired
    private lateinit var service: TodosCountNotificationServiceImpl

    override fun accept(e: Event<TodosCountNotification>?) {
        val data = e?.data
        data?.let {
            service.notify(data)
        }
    }

}