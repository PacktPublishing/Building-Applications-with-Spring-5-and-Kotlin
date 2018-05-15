package com.journaler.api.reactor

import reactor.bus.Event
import reactor.fn.Consumer

interface NotificationConsumer<T> : Consumer<Event<T>>