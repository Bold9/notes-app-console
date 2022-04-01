import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import models.Note
import org.junit.Test

class NoteAPI {

    private var notes = ArrayList<Note>()

    fun add(note: Note): Boolean {
        return notes.add(note)
    }

    fun listAllNotes(): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {

                listOfNotes += "${i}: ${notes[i]} \n"
            }
            listOfNotes
        }
    }

    fun numberOfNotes(): Int {
        return notes.size
    }

    fun findNote(index: Int): Note? {
        return if (isValidListIndex(index, notes)) {
            notes[index]
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }


    private fun Nothing.findNote(unit: Any) {
        TODO("Not yet implemented")
    }

    private fun Nothing.numberOfNotes() {
        TODO("Not yet implemented")
    }

    fun listActiveNotes(): String {
        return if (notes.isEmpty()) {
            "No notes stored"
        } else {
            var listOfNotes = ""
            for (i in notes.indices) {
                if (!notes[i].isNoteArchived)
                listOfNotes += "${i}: ${notes[i]} \n"
            }
            listOfNotes
        }
    }

        fun listArchivedNotes(): String {
            return if (numberOfArchivedNotes() == 0) {
                "No Archived notes stored"
            } else {
                var listOfNotes = ""
                for (i in notes.indices) {
                    if (notes[i].isNoteArchived)
                        listOfNotes += "${i}: ${notes[i]} \n"
                }
                listOfNotes
            }
        }


    fun numberOfActiveNotes(): Int {
        var counter = 0
        for (note in notes) {
            if (!note.isNoteArchived) {
                counter++
            }
        }
        return counter
    }

    fun numberOfArchivedNotes(): Int {
        var counter = 0
        for (note in notes) {
            if (note.isNoteArchived) {
                counter++
            }
        }
        return counter
    }

    fun deleteNote(indexToDelete: Int): Note? {
        return if (isValidListIndex(indexToDelete, notes)) {
            notes.removeAt(indexToDelete)
        } else null
    }
}



