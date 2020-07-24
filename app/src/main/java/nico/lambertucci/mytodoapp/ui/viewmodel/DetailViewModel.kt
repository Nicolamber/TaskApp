package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.ui.taskDatabase

class DetailViewModel : ViewModel() {

    private var task = MutableLiveData<Task>()
    private val database =  taskDatabase.taskDAO()

    fun getTaskById(taskId: Int): LiveData<Task>{
        task.value = database.getTaskById(taskId)
        return task
    }

}