package nico.lambertucci.mytodoapp.utils

class AuthenticationUtilities {

    fun validateUserAndPass(username: String, password: String): Boolean {
        return (username.length < 15 && password.length >= 8)
    }

    fun checkPassword(newPassword: String, repeatedPassword: String): Boolean{
        return newPassword == repeatedPassword && repeatedPassword.isNotEmpty()
    }

}