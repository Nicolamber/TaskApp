package nico.lambertucci.mytodoapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.di.Injection
import nico.lambertucci.mytodoapp.ui.viewmodel.LoginViewModel
import nico.lambertucci.mytodoapp.utils.AuthenticationUtilities

lateinit var taskAuthor:String

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, Injection.getViewModelFactory()).get(LoginViewModel::class.java)

        loginUser.setOnClickListener {
            if (loginUser()) {
                findNavController().navigate(R.id.overviewScreen)
            }
        }
        registerUser.setOnClickListener {
            findNavController().navigate(R.id.signUp, null)
        }
    }

    private fun loginUser(): Boolean {

         taskAuthor = username.editText?.text.toString()
        val pass = password.editText?.text.toString()

        if (viewModel.checkFields(taskAuthor, pass)) {
            return if (viewModel.loginUser(taskAuthor, pass)) {
                true
            } else {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(getString(R.string.errorTitle))
                builder.setMessage(getString(R.string.userOrPassInvalid))
                builder.setPositiveButton(android.R.string.ok) { _, _ ->
                    username.editText?.text = null
                    password.editText?.text = null
                }
                builder.show()
                false
            }
        }
        return false
    }


}