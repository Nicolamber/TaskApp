package nico.lambertucci.mytodoapp

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val userDatabase = taskDatabase.userDAO()

    fun loginUser(username: String, password: String): Boolean {
        val user = userDatabase.verifyUserById(username)
        return user.password == password
    }
}