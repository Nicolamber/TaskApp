package nico.lambertucci.mytodoapp.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey val taskId: Int,
    val taskName: String,
    val taskDescription: String?,
    val taskStatus: String,
    val isFavorite: Boolean,
    val author: String,
    val taskSize: Int
)