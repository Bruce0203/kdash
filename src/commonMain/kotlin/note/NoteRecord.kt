package note

import BackGrid
import audit.Audit
import audit.AuditType
import audit.NoteAuditEvent
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.center
import com.soywiz.korge.view.position
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.tween.moveTo
import com.soywiz.korge.view.tween.scaleTo
import com.soywiz.korim.color.Colors
import com.soywiz.korma.interpolation.Easing
import coroutine
import delay
import event.api.listen
import event.api.publish
import event.impl.NoteSpawnEvent
import event.impl.UpdateEvent
import kotlin.random.Random

class NoteRecord(backGrid: BackGrid, stage: Stage) {init { stage.apply {
    val notePool = ArrayList<Note>()

    val speed = 5.seconds

    listen(UpdateEvent::class) {
        if (stage.input.keys.pressing(Key.S)) {
            val noteLine = Random.nextInt(0, backGrid.LINE_AMOUNT)
            val note =
                backGrid.noteContainer.solidRect(backGrid.lineWidth, backGrid.clickBar, color = Colors.AQUAMARINE) {
                    position(
                        backGrid.lineWidth * noteLine - backGrid.halfWidth + backGrid.lineWidth / 2,
                        -backGrid.clickBar - backGrid.halfHeight
                    )
                    center()
                }
            val oNote = Note(note)
            coroutine {
                note.moveTo(note.x, stage.height, time = speed, easing = Easing.LINEAR)
            }
            delay(speed) {
                listen(UpdateEvent::class) {
                    note.removeFromParent()
                    notePool.remove(oNote); unregister()
                }
            }
            notePool.add(oNote)
            publish(NoteSpawnEvent(oNote))
        } else if (stage.input.keys.justPressed(Key.SPACE)) {
            notePool.forEach { note ->
                Audit(backGrid, note)
            }
        }
    }
    listen(NoteAuditEvent::class) { event ->
        backGrid.gridContainer.apply {
            val origin = scale
            val time = 0.03.seconds
            coroutine {
                scaleTo(origin+.05, origin+.05, time = time, easing = Easing.EASE)
                scaleTo(origin, origin, time = time, easing = Easing.EASE)
            }
        }
    }
} } }