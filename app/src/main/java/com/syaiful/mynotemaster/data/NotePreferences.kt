package com.syaiful.mynotemaster.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.syaiful.mynotemaster.model.Note

class NotePreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("note_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveNotes(notes: List<Note>) {
        val json = gson.toJson(notes)
        sharedPreferences.edit().putString("notes_list", json).apply()
    }

    fun getNotes(): List<Note> {
        val json = sharedPreferences.getString("notes_list", null) ?: return emptyList()
        val type = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(json, type)
    }
}
