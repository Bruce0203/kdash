package audit

import BackGrid
import com.soywiz.klock.seconds
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.position
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors
import com.soywiz.korim.text.HorizontalAlign
import com.soywiz.korim.text.VerticalAlign
import delay
import event.api.Event
import event.api.publish
import note.Note
import kotlin.math.abs

class Audit(backGrid: BackGrid, note: Note) {
    init {
        val noteY = note.rectBase.y
        val auditLine = backGrid.auditLine
        val distance = noteY - auditLine
        println(distance)
        val perfectRange = 30
        val goodRange = 60
        val auditType =
            if (abs(distance) < perfectRange) AuditType.PERFECT
            else if (abs(distance) < goodRange)
                if (distance < 0) AuditType.FAST else AuditType.SLOW
            else AuditType.MISS
        println(auditType.toString())
        auditType.block(backGrid.auditContainer).apply {
            position(note.rectBase.pos)
            fontSize = 30.0
            delay(1.seconds) { removeFromParent() }
            centerText()
        }
        publish(NoteAuditEvent(note, auditType))
    }
}

fun Text.centerText() {
    alignment = alignment
        .withHorizontal(HorizontalAlign.CENTER)
        .withVertical(VerticalAlign.MIDDLE)
}

data class NoteAuditEvent(val note: Note, val auditType: AuditType) : Event

enum class AuditType(val block: (Container) -> Text) {
    PERFECT({it.text("Perfect!", color = Colors.GREEN)}),
    SLOW({it.text("Slow!!", color = Colors.YELLOW)}),
    FAST({it.text("Fast!!", color = Colors.MEDIUMVIOLETRED)}),
    MISS({it.text("Miss!!", color = Colors.RED)}),
}