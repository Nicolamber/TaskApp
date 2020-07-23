package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.ui.taskDatabase
import java.lang.Exception

class AddTaskViewModel : ViewModel() {

    private val newTaskDB = taskDatabase.taskDAO()

    fun addTask(taskName: String, taskDescription: String?, isFavorite: Boolean, author: String?): Boolean {
        return try {
            newTaskDB.insertTask(Task(taskName,taskDescription,isFavorite,author!!))
            true
        }catch (e:Exception){
            false
        }
    }

}