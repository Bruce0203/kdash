package io.github.bruce0203.kdash.event.api

class ListenerHolder(private val listener: Listener<*>) {

    fun unregister() {
        unregister(listener)
    }

}