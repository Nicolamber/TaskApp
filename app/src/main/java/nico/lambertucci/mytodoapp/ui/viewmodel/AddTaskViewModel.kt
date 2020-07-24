package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.ui.taskDatabase
import java.lang.Exception

class AddTaskViewModel(private val repository: TaskRepository) : ViewModel() {

    fun addTask(taskName: String, taskDescription: String?, isFavorite: Boolean, author: String): Boolean {
        return try {
            repository.addNewTask(Task(taskName,taskDescription,isFavorite,author))
            true
        }catch (e:Exception){
            Log.e(VIEWMODELS_TAG, e.localizedMessage)
            false
        }
    }

}