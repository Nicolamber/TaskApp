package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.ui.taskDatabase
import java.lang.NullPointerException

class FavoritesViewModel(private val repository: TaskRepository) : ViewModel() {

    fun getFavorites(isFavorite: Boolean, taskAuthor: String): LiveData<List<Task>>? {
        val favTasks: LiveData<List<Task>>?
        try {
            favTasks = repository.getFavoritesTasks(isFavorite, taskAuthor)
        } catch (e: NullPointerException) {
            Log.e(VIEWMODELS_TAG, e.localizedMessage)
            throw e
        }
        return favTasks
    }
}