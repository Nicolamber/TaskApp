package nico.lambertucci.mytodoapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey val taskId:Int,
    val taskName: String,
    val taskDescription: String?,
    val taskStatus:String)