package nico.lambertucci.mytodoapp.domain

import androidx.room.*
import nico.lambertucci.mytodoapp.domain.database.Task

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task WHERE task.`author` = :author")
    fun getAllTasks(author: String): List<Task>

    @Query("SELECT * FROM task WHERE task.`taskId` = :taskId")
    fun getTaskById(taskId: Int): Task?

    @Query("SELECT * FROM task WHERE task.`isFavorite` = :favorite AND task.`author` = :author")
    fun getFavoriteTasks(favorite:Boolean, author: String): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}