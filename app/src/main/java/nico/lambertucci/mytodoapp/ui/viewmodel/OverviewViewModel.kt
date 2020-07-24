package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.ui.taskDatabase

class OverviewViewModel : ViewModel() {
    private var task = MutableLiveData<List<Task>>()
    private val taskDB = taskDatabase.taskDAO()

    fun getTasks(): LiveData<List<Task>>{
        task.value = taskDB.getAllTasks()
        return task
    }
}