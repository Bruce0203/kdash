package event.api

import com.soywiz.kds.toFastList
import kotlin.reflect.KClass

private val eventRecord = HashMap<KClass<out Event>, ArrayList<Listener<Event>>>()

private fun <EVENT : Event> listen(event: KClass<EVENT>, listener: Listener<EVENT>) {
    eventRecord[event] = (eventRecord[event] ?: ArrayList()).apply { add(listener) }
}

fun <EVENT : Event> listen(event: KClass<EVENT>, listener: ListenerHolder.(EVENT) -> Unit) {
    listen(event, object : Listener<EVENT> {
        override fun listen(event: EVENT) = ListenerHolder(this).listener(event)
    })
}

fun <EVENT : Event> unregister(listener: Listener<EVENT>) {
    eventRecord.any { it.value.remove(listener) }
}


fun <EVENT : Event> publish(event: EVENT) {
    eventRecord[event::class]?.toFastList()?.forEach { it.listen(event) }
}