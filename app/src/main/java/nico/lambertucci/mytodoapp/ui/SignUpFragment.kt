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
import nico.lambertucci.mytodoapp.di.Injection
import nico.lambertucci.mytodoapp.ui.viewmodel.SignUpViewModel

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
        viewModel = ViewModelProvider(this, Injection.getViewModelFactory()).get(SignUpViewModel::class.java)
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
        if (viewModel.checkFields(newUsername,newUserPassword, repeatedPass)){
            return if (viewModel.checkPassword(newUserPassword,repeatedPass)){
                if (viewModel.signUp(newUsername,newUserPassword)){
                    taskAuthor = newUsername
                    true
                }else{
                    showErrorDialog(getString(R.string.errorSavingUser),true)

                    false
                }
            }else{
                showErrorDialog(getString(R.string.passwordError),false)
                false
            }

        }else{
            showErrorDialog(getString(R.string.emptySignUpFields),false)
            return false
        }
    }


    private fun showErrorDialog(errorMessage: String, clearUserFields: Boolean){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.errorTitle))
        builder.setMessage(errorMessage)
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            if (clearUserFields) {
                newUser.editText?.text = null
            }
            newPassword.editText?.text = null
            repeatPassword.editText?.text = null
        }
        builder.show()
    }
}