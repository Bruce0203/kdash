package event.api

interface Listener<out EVENT : Event> {

    fun listen(event: @UnsafeVariance EVENT)

}