package nico.lambertucci.mytodoapp.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nico.lambertucci.mytodoapp.domain.TaskDAO
import nico.lambertucci.mytodoapp.domain.UserDAO
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.database.User
import nico.lambertucci.mytodoapp.ui.taskDatabase
import java.lang.Exception
import java.lang.NullPointerException

class TaskRepositoryImpl() : TaskRepository {

    private val userDaoDatabase: UserDAO = taskDatabase.userDAO()
    private val taskDaoDatabase: TaskDAO = taskDatabase.taskDAO()
    private val mutableTaskList = MutableLiveData<List<Task>>()
    private val specificTask = MutableLiveData<Task>()

    companion object {
        private const val REPOSITORY_TAG = "RepositoryLog"
    }

    override fun getUserByNameAndPass(username: String, password: String): Boolean {
        val recoveredUser = userDaoDatabase.verifyUserById(username)
        if (recoveredUser != null && recoveredUser.password == password) {
            Log.i(REPOSITORY_TAG, "User exist")
            return true
        } else {
            Log.e(REPOSITORY_TAG, "User does not exist!")
            throw NullPointerException("User not found")
        }
    }

    override fun registerNewUser(user: User): Boolean {
        val usersIndex = userDaoDatabase.getAllUsers().size
        userDaoDatabase.insertUser(user)
        if (usersIndex < userDaoDatabase.getAllUsers().size) {
            Log.i(REPOSITORY_TAG, "User inserted successfully")
            return true
        } else {
            Log.e(REPOSITORY_TAG, "Could not insert new user")
            throw Exception("Insertion failed")
        }
    }

    override fun getAllTasks(author: String): LiveData<List<Task>> {
        val tasksList = taskDaoDatabase.getAllTasks(author)
        if (tasksList.isNotEmpty()) {
            mutableTaskList.value = tasksList
            Log.i(REPOSITORY_TAG, "List retrieved succesfully")
            return mutableTaskList
        } else {
            Log.e(REPOSITORY_TAG, "TaskList is Null or Empty")
            throw NullPointerException("Tasklist is Null or empty")
        }
    }

    override fun addNewTask(task: Task): Boolean {
        val taskIndex = taskDaoDatabase.getAllTasks(task.author).size
        taskDaoDatabase.insertTask(task)
        if (taskIndex < taskDaoDatabase.getAllTasks(task.author).size) {
            Log.i(REPOSITORY_TAG, "Task inserted successfully")
            return true
        } else {
            Log.e(REPOSITORY_TAG, "Could not insert new task")
            throw Exception("Insertion failed")
        }
    }

    override fun getTaskById(taskId: Int): LiveData<Task> {
        val task = taskDaoDatabase.getTaskById(taskId)
        if (task != null) {
            specificTask.value = task
            Log.i(REPOSITORY_TAG, "Retrieved task by ID")
            return specificTask
        } else {
            Log.e(REPOSITORY_TAG, "Could not find the specific task")
            throw NullPointerException("There is no task for the id: $taskId")
        }
    }

    override fun getFavoritesTasks(isFavorite: Boolean, author: String): LiveData<List<Task>> {
        val favorites = taskDaoDatabase.getFavoriteTasks(isFavorite, author)
        if (favorites.isNotEmpty()) {
            mutableTaskList.value = favorites
            Log.i(REPOSITORY_TAG, "Successfully retrieved favorites")
            return mutableTaskList
        } else {
            Log.e(REPOSITORY_TAG, "Could not retrieve the favorites")
            throw NullPointerException("Favorites are empty")
        }
    }


}