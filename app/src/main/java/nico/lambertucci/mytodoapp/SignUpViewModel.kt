package nico.lambertucci.mytodoapp

import androidx.lifecycle.ViewModel
import nico.lambertucci.mytodoapp.domain.User

class SignUpViewModel : ViewModel() {

    private val userDatabase = taskDatabase.userDAO()

    fun signUp(username: String, password: String): Boolean{
        if (username.isNotEmpty() && password.isNotEmpty()){
            userDatabase.insertUser(User(username,password))
            return true
        }
        return false
    }
}