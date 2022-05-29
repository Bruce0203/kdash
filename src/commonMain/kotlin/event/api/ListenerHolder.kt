package event.api

class ListenerHolder(private val listener: Listener<*>) {

    fun unregister() {
        unregister(listener)
    }

}