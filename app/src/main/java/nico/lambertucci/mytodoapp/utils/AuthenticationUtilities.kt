package nico.lambertucci.mytodoapp.utils

class AuthenticationUtilities {

    fun validateFieldsForLogin(username: String, password: String): Boolean {
        return (username.length < 15 && password.length >= 8)
    }

    fun validateFieldsForSignUp(username: String, password: String, newPassword: String): Boolean {
        return (username.length < 15 && password.length >= 8&& newPassword.length >= 8 )
    }

    fun checkPassword(newPassword: String, repeatedPassword: String): Boolean{
        return newPassword == repeatedPassword && repeatedPassword.isNotEmpty()
    }

}