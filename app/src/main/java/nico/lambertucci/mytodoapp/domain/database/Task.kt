package nico.lambertucci.mytodoapp.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val taskName: String,
    val taskDescription: String?,
    val isFavorite: Boolean,
    val author: String
){
    @PrimaryKey(autoGenerate = true) var taskId: Int? = null
}