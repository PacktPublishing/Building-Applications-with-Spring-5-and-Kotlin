package com.journaler.api.reactor

interface NotificationService<in T> {

    fun notify(notification: T)

}