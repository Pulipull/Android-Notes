package com.syaiful.mynotemaster.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.syaiful.mynotemaster.data.NotePreferences
import com.syaiful.mynotemaster.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * NoteViewModel = "otak" aplikasi yang menyimpan dan mengelola semua catatan.
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = NotePreferences(application)

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        _notes.value = prefs.getNotes().sortedWith(
            compareByDescending<Note> { it.isPinned }
                .thenByDescending { it.updatedAt }
        )
    }

    /** Mencari catatan berdasarkan id — dipakai EditorScreen saat mode edit. */
    fun getNoteById(id: Long): Note? {
        return _notes.value.find { it.id == id }
    }

    /**
     * Menyimpan catatan ke SharedPreferences.
     */
    fun saveNote(
        id: Long? = null,
        content: String,
        color: Long = 0xFFFFF9C4,
        isPinned: Boolean = false
    ) {
        if (content.isBlank()) return

        val currentList = _notes.value.toMutableList()
        val existingNoteIndex = id?.let { currentId ->
            currentList.indexOfFirst { it.id == currentId }
        }

        if (existingNoteIndex != null && existingNoteIndex != -1) {
            val existingNote = currentList[existingNoteIndex]
            val updatedNote = Note(
                id = existingNote.id,
                content = content.trim(),
                color = color,
                isPinned = isPinned,
                createdAt = existingNote.createdAt,
                updatedAt = System.currentTimeMillis()
            )
            currentList[existingNoteIndex] = updatedNote
        } else {
            val newNote = Note(
                id = System.currentTimeMillis(),
                content = content.trim(),
                color = color,
                isPinned = isPinned
            )
            currentList.add(newNote)
        }

        prefs.saveNotes(currentList)
        loadNotes()
    }

    /** Menghapus catatan. */
    fun deleteNote(id: Long) {
        val currentList = _notes.value.toMutableList()
        currentList.removeAll { it.id == id }
        prefs.saveNotes(currentList)
        loadNotes()
    }
}