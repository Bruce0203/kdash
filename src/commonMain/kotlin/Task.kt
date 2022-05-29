import com.soywiz.klock.DateTime
import com.soywiz.klock.TimeSpan
import event.api.listen
import event.impl.UpdateEvent

fun delay(delay: TimeSpan, block: () -> Unit) {
    val start = DateTime.now()
    listen(UpdateEvent::class) {
        if (DateTime.now() - start > delay) {
            block()
            unregister()
        }
    }
}