package nico.lambertucci.mytodoapp.di

import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.domain.repository.TaskRepositoryImpl
import nico.lambertucci.mytodoapp.ui.viewmodel.ViewModelFactory

object Injection {

    private val tasksDataSource = TaskRepositoryImpl()
    private val taskViewModel = ViewModelFactory(tasksDataSource)

    fun getRepository(): TaskRepository{
        return  tasksDataSource
    }

    fun getViewModelFactory(): ViewModelProvider.Factory{
        return taskViewModel
    }
}