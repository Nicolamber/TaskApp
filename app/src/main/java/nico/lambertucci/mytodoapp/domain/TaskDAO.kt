package nico.lambertucci.mytodoapp.domain

import androidx.room.*
import nico.lambertucci.mytodoapp.domain.database.Task

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM task WHERE task.`taskId` = :taskId")
    fun getTaskById(taskId: String): Task

    @Query("SELECT * FROM task WHERE task.`isFavorite` = :favorite")
    fun getFavoriteTasks(favorite:Boolean): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

}