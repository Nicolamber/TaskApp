package nico.lambertucci.mytodoapp.domain.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val username: String,
    val password: String
)