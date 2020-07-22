package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.database.User
import nico.lambertucci.mytodoapp.ui.taskDatabase

class SignUpViewModel : ViewModel() {

    private val userDatabase = taskDatabase.userDAO()

    fun signUp(username: String, password: String): Boolean{
        if (username.isNotEmpty() && password.isNotEmpty()){
            userDatabase.insertUser(
                User(
                    username,
                    password
                )
            )
            return true
        }
        return false
    }
}