package com.journaler.api.service.reactor

interface NotificationService<in T> {

    fun notify(notification: T)

}