import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.interpolation.*
import event.api.call
import event.api.listen
import event.impl.TestEvent

suspend fun main() = Korge(width = 720, height = 512, bgcolor = Colors["#2b2b2b"]) {

    listen(TestEvent::class) {
        println(it.name)
    }

    call(TestEvent("asdf"))

}