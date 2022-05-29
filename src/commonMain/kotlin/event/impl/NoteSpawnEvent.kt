package event.impl

import note.Note
import event.api.Event

data class NoteSpawnEvent(val note: Note) : Event
