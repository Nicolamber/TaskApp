package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.utils.AuthenticationUtilities
import java.lang.NullPointerException

class LoginViewModel(private val repository: TaskRepository) : ViewModel() {

    private val authUtils = AuthenticationUtilities()

    fun loginUser(username: String, password: String): Boolean {
        return try {
            repository.getUserByNameAndPass(username, password)
        }catch (e: NullPointerException){
            Log.e(VIEWMODELS_TAG, e.localizedMessage)
            false
        }
    }

    fun checkFields(username: String, password: String): Boolean{
        return authUtils.validateFieldsForLogin(username,password)
    }
}