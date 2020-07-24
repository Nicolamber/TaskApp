package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository

const val VIEWMODELS_TAG = "ViewmodelLog"
class ViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when (modelClass) {
            LoginViewModel::class.java -> {
                return LoginViewModel(this.repository) as T
            }
            SignUpViewModel::class.java -> {
                return SignUpViewModel(this.repository) as T
            }
            OverviewViewModel::class.java -> {
                return OverviewViewModel(this.repository) as T
            }
            FavoritesViewModel::class.java -> {
                return FavoritesViewModel(this.repository) as T
            }
            DetailViewModel::class.java -> {
                return DetailViewModel(this.repository) as T
            }
            AddTaskViewModel::class.java -> {
                return AddTaskViewModel(this.repository) as T
            }

            else -> throw IllegalArgumentException("ViewModel Not Found")

        }
    }
}