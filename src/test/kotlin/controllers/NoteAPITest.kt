package controllers

import NoteAPI
import models.Note
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class NoteAPITest {

    private var learnKotlin: Note? = null
    private var summerHoliday: Note? = null
    private var codeApp: Note? = null
    private var testApp: Note? = null
    private var swim: Note? = null
    private var populatedNotes: NoteAPI? = NoteAPI()
    private var emptyNotes: NoteAPI? = NoteAPI()

    @BeforeEach
    fun setup() {
        learnKotlin = Note("Learning Kotlin", 5, "College", false)
        summerHoliday = Note("Summer Holiday to France", 1, "Holiday", false)
        codeApp = Note("Code App", 4, "Work", true)
        testApp = Note("Test App", 4, "Work", false)
        swim = Note("Swim - Pool", 3, "Hobby", true)

        //adding 5 Note to the notes api
        populatedNotes!!.add(learnKotlin!!)
        populatedNotes!!.add(summerHoliday!!)
        populatedNotes!!.add(codeApp!!)
        populatedNotes!!.add(testApp!!)
        populatedNotes!!.add(swim!!)
    }

    @AfterEach
    fun tearDown() {
        learnKotlin = null
        summerHoliday = null
        codeApp = null
        testApp = null
        swim = null
        populatedNotes = null
        emptyNotes = null
    }

    @Nested
    inner class AddNotes {
        @Test
        fun `adding a Note to a populated list adds to ArrayList`() {
            val newNote = Note("Study Lambdas", 1, "College", false)
            assertEquals(5, populatedNotes!!.numberOfNotes())
            assertTrue(populatedNotes!!.add(newNote))
            assertEquals(6, populatedNotes!!.numberOfNotes())
            assertEquals(newNote, populatedNotes!!.findNote(populatedNotes!!.numberOfNotes() - 1))
        }

        @Test
        fun `adding a Note to an empty list adds to ArrayList`() {
            val newNote = Note("Study Lambdas", 1, "College", false)
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.add(newNote))
            assertEquals(1, emptyNotes!!.numberOfNotes())
            assertEquals(newNote, emptyNotes!!.findNote(emptyNotes!!.numberOfNotes() - 1))
        }
    }

    @Nested
    inner class ListNotes {

        @Test
        fun `listAllNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listAllNotes().lowercase().contains("no notes"))
        }

        @Test
        fun `listAllNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val notesString = populatedNotes!!.listAllNotes().lowercase()
            assertTrue(notesString.contains("learning kotlin"))
            assertTrue(notesString.contains("code app"))
            assertTrue(notesString.contains("test app"))
            assertTrue(notesString.contains("swim"))
            assertTrue(notesString.contains("summer holiday"))
        }
    }


    @Nested
    inner class listActiveNotes {

        @Test
        fun `listActiveNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listAllNotes().lowercase().contains("no notes"))
        }

        @Test
        fun `listActiveNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val notesString = populatedNotes!!.listActiveNotes().lowercase()
            assertTrue(notesString.contains("learning kotlin"))
            assertFalse(notesString.contains("code app"))
            assertTrue(notesString.contains("test app"))
            assertFalse(notesString.contains("swim"))
            assertTrue(notesString.contains("summer holiday"))
        }
    }

    @Nested
    inner class ListArchivedNotes {

        @Test
        fun `listArchivedNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfNotes())
            assertTrue(emptyNotes!!.listAllNotes().lowercase().contains("no notes"))
        }

        @Test
        fun `listArchivedNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(5, populatedNotes!!.numberOfNotes())
            val notesString = populatedNotes!!.listArchivedNotes().lowercase()
            assertFalse(notesString.contains("learning kotlin"))
            assertTrue(notesString.contains("code app"))
            assertFalse(notesString.contains("test app"))
            assertTrue(notesString.contains("swim"))
            assertFalse(notesString.contains("summer holiday"))
        }
    }

    @Nested
    inner class numberOfArchivedNotes {

        @Test
        fun `numberOfArchivedNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfArchivedNotes())
            assertTrue(emptyNotes!!.listArchivedNotes().lowercase().contains("no archived notes stored"))
        }

        @Test
        fun `numberOfArchivedNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(2, populatedNotes!!.numberOfArchivedNotes())
            val notes = populatedNotes!!.listArchivedNotes().lowercase()
            assertFalse(notes.contains("learning kotlin"))
            assertTrue(notes.contains("code app"))
            assertFalse(notes.contains("test app"))
            assertTrue(notes.contains("swim"))
            assertFalse(notes.contains("summer holiday"))
        }
    }

    @Nested
    inner class numberOfActiveNotes {

        @Test
        fun `numberOfActiveNotes returns No Notes Stored message when ArrayList is empty`() {
            assertEquals(0, emptyNotes!!.numberOfActiveNotes())
            assertTrue(emptyNotes!!.listActiveNotes().lowercase().contains("no notes stored"))
        }

        @Test
        fun `numberOfActiveNotes returns Notes when ArrayList has notes stored`() {
            assertEquals(3, populatedNotes!!.numberOfActiveNotes())
            val notes = populatedNotes!!.listActiveNotes().lowercase()
            assertTrue(notes.contains("learning kotlin"))
            assertFalse(notes.contains("code app"))
            assertTrue(notes.contains("test app"))
            assertFalse(notes.contains("swim"))
            assertTrue(notes.contains("summer holiday"))
        }
    }

    @Nested
    inner class DeleteNotes {

        @Test
        fun `deleting a Note that does not exist, returns null`() {
            assertNull(emptyNotes!!.deleteNote(0))
            assertNull(populatedNotes!!.deleteNote(-1))
            assertNull(populatedNotes!!.deleteNote(5))
        }

        @Test
        fun `deleting a note that exists delete and returns deleted object`() {
            assertEquals(5, populatedNotes!!.numberOfNotes())
            assertEquals(swim, populatedNotes!!.deleteNote(4))
            assertEquals(4, populatedNotes!!.numberOfNotes())
            assertEquals(learnKotlin, populatedNotes!!.deleteNote(0))
            assertEquals(3, populatedNotes!!.numberOfNotes())
        }
    }
}

@Nested
inner class UpdateNotes {
    @Test
    fun `updating a note that does not exist returns false`(){
        assertFalse(populatedNotes!!.updateNote(6, Note("Updating Note", 2, "Work", false)))
        assertFalse(populatedNotes!!.updateNote(-1, Note("Updating Note", 2, "Work", false)))
        assertFalse(emptyNotes!!.updateNote(0, Note("Updating Note", 2, "Work", false)))
    }

    @Test
    fun `updating a note that exists returns true and updates`() {
        //check note 5 exists and check the contents
        assertEquals(swim, populatedNotes!!.findNote(4))
        assertEquals("Swim - Pool", populatedNotes!!.findNote(4)!!.noteTitle)
        assertEquals(3, populatedNotes!!.findNote(4)!!.notePriority)
        assertEquals("Hobby", populatedNotes!!.findNote(4)!!.noteCategory)

        //update note 5 with new information and ensure contents updated successfully
        assertTrue(populatedNotes!!.updateNote(4, Note("Updating Note", 2, "College", false)))
        assertEquals("Updating Note", populatedNotes!!.findNote(4)!!.noteTitle)
        assertEquals(2, populatedNotes!!.findNote(4)!!.notePriority)
        assertEquals("College", populatedNotes!!.findNote(4)!!.noteCategory)
    }
}

