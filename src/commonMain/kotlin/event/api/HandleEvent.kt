package event.api

import kotlin.reflect.KClass

private val eventRecord = HashMap<KClass<out Event>, ArrayList<Listener<Event>>>()

private fun <EVENT : Event> listen(event: KClass<EVENT>, listener: Listener<EVENT>) {
    eventRecord[event] = (eventRecord[event] ?: ArrayList()).apply { add(listener) }
}

fun <EVENT : Event> listen(event: KClass<EVENT>, listener: (EVENT) -> Unit) {
    listen(event, object : Listener<EVENT> {
        override fun listen(event: EVENT) = listener(event)
    })
}

fun <EVENT : Event> call(event: EVENT) {
    eventRecord[event::class]?.forEach { it.listen(event) }
}