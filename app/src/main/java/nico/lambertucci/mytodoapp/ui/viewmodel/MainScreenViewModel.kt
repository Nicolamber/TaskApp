package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.ui.taskDatabase

class MainScreenViewModel : ViewModel() {
    var task = MutableLiveData<List<Task>>()
    private val taskDB = taskDatabase.taskDAO()

    fun getTasks(): LiveData<List<Task>>{
        //ui testing purpose
        taskDB.insertTask(Task(1,"comprar comida","","open",true,"nico",2))
        taskDB.insertTask(Task(2,"comprar ","","open",false,"nico",2))
        task.value = taskDB.getAllTasks()
        return task
    }
}