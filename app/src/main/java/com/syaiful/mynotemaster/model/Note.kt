package com.syaiful.mynotemaster.model

/**
 * Note adalah "cetakan" (blueprint) satu catatan.
 */
data class Note(
    val id: Long = System.currentTimeMillis(), // Menggunakan timestamp sebagai ID unik sederhana
    val content: String,
    val color: Long = 0xFFFFF9C4, // Default Kuning Muda
    val isPinned: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)