package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import java.lang.NullPointerException

class DetailViewModel(private val repository: TaskRepository) : ViewModel() {


    fun getTaskById(taskId: Int): LiveData<Task>? {
        val task : LiveData<Task>?
        try {
            task = repository.getTaskById(taskId)
        }catch (e: NullPointerException){
            Log.e(VIEWMODELS_TAG, e.localizedMessage)
            throw e
        }
        return task
    }

}