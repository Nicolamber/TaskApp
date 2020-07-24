package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.ui.taskDatabase
import java.lang.NullPointerException

class OverviewViewModel(private val repository: TaskRepository) : ViewModel() {

    fun getTasks(taskAuthor: String): LiveData<List<Task>>? {
        val task: LiveData<List<Task>>?
        try {
            task = repository.getAllTasks(taskAuthor)
        } catch (e: NullPointerException) {
            Log.e(VIEWMODELS_TAG, e.localizedMessage)
            throw e
        }
        return task
    }
}