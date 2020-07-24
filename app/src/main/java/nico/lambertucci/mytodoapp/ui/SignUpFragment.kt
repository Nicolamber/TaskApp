package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.sign_up_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.viewmodel.SignUpViewModel
import nico.lambertucci.mytodoapp.utils.AuthenticationUtilities

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.actionBar?.title = "Nuevo usuario"
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        signUpNewUser.setOnClickListener {
            if (signUpNewUser()) {
                findNavController().navigate(R.id.overviewScreen, null)
            }
        }
    }

    private fun signUpNewUser(): Boolean {
        val newUsername = newUser.editText?.text.toString()
        val newUserPassword = newPassword.editText?.text.toString()
        val repeatedPass = repeatPassword.editText?.text.toString()
        if (AuthenticationUtilities().validateUserAndPass(newUsername, newUserPassword)) {
            if (AuthenticationUtilities().checkPassword(newUserPassword, repeatedPass)) {
                return (viewModel.signUp(newUsername, newUserPassword))
            } else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Error!")
                builder.setMessage("Las contraseñas no coinciden.")
                builder.setPositiveButton(android.R.string.ok) { _, _ ->
                    newUser.editText?.text = null
                    newPassword.editText?.text = null
                    repeatPassword.editText?.text = null
                }
                builder.show()
            }
        } else {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Error!")
            builder.setMessage("Usuario o contraseña incorrecto.")
            builder.setPositiveButton(android.R.string.ok) { _, _ ->
                newUser.editText?.text = null
                newPassword.editText?.text = null
                repeatPassword.editText?.text = null
            }
            builder.show()
        }
        return false
    }
}