package nico.lambertucci.mytodoapp.domain

import androidx.room.*

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE task.`taskId` = :taskId")
    fun loadAllByIds(taskId: String): List<Task>

    @Query("SELECT * FROM task WHERE task.`taskName` = :taskName")
    fun findByName(taskName: String): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(task: Task)

    @Delete
    fun delete(task: Task)

}