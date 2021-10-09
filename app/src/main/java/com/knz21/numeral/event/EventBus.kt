package com.knz21.numeral.event

import kotlinx.coroutines.flow.*

object EventBus {

    private val _events = MutableSharedFlow<Event>()

    val events: SharedFlow<Event> get() = _events.asSharedFlow()

    inline fun <reified T> events(): Flow<T> = events.filter { it is T }.map { it as T }

    suspend fun post(event: Event) {
        _events.emit(event)
    }

    interface Event
}