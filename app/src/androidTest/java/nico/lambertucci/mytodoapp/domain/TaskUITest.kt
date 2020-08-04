package nico.lambertucci.mytodoapp.domain

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.database.TaskDatabase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskUITest {

    private var database: TaskDatabase
    private var taskDao: TaskDAO

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            TaskDatabase::class.java
        ).build()

        taskDao = database.taskDAO()
    }

    @Test
    fun insertValidTask() {
        val task = Task("Buy Food", "this is a test",false,"Nicolas")
        taskDao.insertTask(task)

        val taskResult = taskDao.getTaskById(1)

        assertTrue(taskResult?.taskName == task.taskName)
        assertTrue(taskResult?.taskDescription == task.taskDescription)
        assertTrue(taskResult?.isFavorite == task.isFavorite)
        assertTrue(taskResult?.author == "Nicolas")
    }

    @Test
    fun insertTaskWithoutDescriptionCanBeValid() {
        val task = Task("Buy Food", null,false,"Nicolas")
        taskDao.insertTask(task)

        val taskResult = taskDao.getTaskById(1)

        assertTrue(taskResult?.taskName == task.taskName)
        assertTrue(taskResult?.taskDescription == null)
        assertTrue(taskResult?.isFavorite == task.isFavorite)
        assertTrue(taskResult?.author == "Nicolas")
    }

    @Test
    fun getFavoritesTaskShouldReturnOnlyFavorites() {
        val favTask1 = Task("Buy Food", null,true,"Nicolas")
        val favTask2 = Task("Test", "Test task",true,"Nicolas")
        val normalTask = Task("Common Task", null,false,"Nicolas")

        taskDao.insertTask(favTask1)
        taskDao.insertTask(favTask2)
        taskDao.insertTask(normalTask)

        val favorites = taskDao.getFavoriteTasks(true,"Nicolas")

        assertTrue(favorites.size == 2)
        assertTrue(favorites[0].taskName == favTask1.taskName)
        assertTrue(favorites[1].taskName == favTask2.taskName)
    }

    @Test
    fun deleteTaskShouldReturnNull() {
        val task = Task("Common Task", null,false,"Nicolas")

        taskDao.insertTask(task)

        val taskResult = taskDao.getTaskById(1)
        assertTrue(taskResult != null)

        taskDao.deleteTask(task)
        assertTrue(taskDao.getTaskById(1) == null)
    }
}