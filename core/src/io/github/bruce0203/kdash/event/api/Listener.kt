package io.github.bruce0203.kdash.event.api

import io.github.bruce0203.kdash.event.api.Event

interface Listener<out EVENT : Event> {

    fun listen(event: @UnsafeVariance EVENT)

}