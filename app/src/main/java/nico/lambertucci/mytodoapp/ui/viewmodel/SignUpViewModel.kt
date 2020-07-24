package nico.lambertucci.mytodoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.User
import nico.lambertucci.mytodoapp.domain.repository.TaskRepository
import nico.lambertucci.mytodoapp.utils.AuthenticationUtilities
import java.lang.Exception

class SignUpViewModel(private val repository: TaskRepository) : ViewModel() {

    private val authUtils = AuthenticationUtilities()

    fun signUp(username: String, password: String): Boolean{
        return try {
            repository.registerNewUser(User(username,password))
            true
        } catch (e: Exception){
            Log.e(VIEWMODELS_TAG,e.localizedMessage)
            false
        }
    }

    fun checkFields(username: String, password: String, repeatedPassword: String): Boolean{
        return authUtils.validateFieldsForSignUp(username,password,repeatedPassword)
    }

    fun checkPassword(password: String, repeatedPassword: String): Boolean{
        return authUtils.checkPassword(password,repeatedPassword)
    }
}