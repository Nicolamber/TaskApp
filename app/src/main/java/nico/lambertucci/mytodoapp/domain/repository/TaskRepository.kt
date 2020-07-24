package nico.lambertucci.mytodoapp.domain.repository

import androidx.lifecycle.LiveData
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.database.User

interface TaskRepository {

    fun getUserByNameAndPass(username: String, password: String): Boolean
    fun registerNewUser(user: User): Boolean
    fun getAllTasks(author: String): LiveData<List<Task>>
    fun addNewTask(task: Task): Boolean
    fun getTaskById(taskId: Int): LiveData<Task>
    fun getFavoritesTasks(isFavorite: Boolean, author: String): LiveData<List<Task>>

}