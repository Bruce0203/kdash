import com.soywiz.korge.*
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.color.*
import event.api.publish
import event.impl.UpdateEvent
import note.NoteRecord

suspend fun main() = Korge(width = 384, height = 1024, bgcolor = Colors["#2b2b2b"]) {

    val backGrid = BackGrid(this)
    NoteRecord(backGrid, this)
    addUpdater { publish(UpdateEvent()) }


}