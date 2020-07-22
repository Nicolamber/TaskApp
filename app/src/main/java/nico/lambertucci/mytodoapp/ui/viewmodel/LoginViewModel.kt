package nico.lambertucci.mytodoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.ui.taskDatabase

class LoginViewModel : ViewModel() {

    private val userDatabase = taskDatabase.userDAO()

    fun loginUser(username: String, password: String): Boolean {
        val user = userDatabase.verifyUserById(username)
        return user.password == password
    }
}