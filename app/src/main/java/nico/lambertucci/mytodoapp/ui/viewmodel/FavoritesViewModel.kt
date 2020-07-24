package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.Task
import nico.lambertucci.mytodoapp.ui.taskDatabase

class FavoritesViewModel : ViewModel() {
    private var favoriteTask = MutableLiveData<List<Task>>()
    private val taskDB = taskDatabase.taskDAO()

    fun getFavorites():LiveData<List<Task>>{
        favoriteTask.value = taskDB.getFavoriteTasks(true)
        return favoriteTask
    }
}