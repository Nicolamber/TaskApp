package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.add_task_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.di.Injection
import nico.lambertucci.mytodoapp.ui.viewmodel.AddTaskViewModel


class AddTaskFragment : Fragment() {

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var taskName: String
    private var taskFav: Boolean = false
    private var taskDescription: String? = null
    private lateinit var newTaskToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_task_fragment, container, false)

        newTaskToolbar = view.findViewById(R.id.newTaskToolbar)
        (activity as AppCompatActivity).setSupportActionBar(newTaskToolbar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTaskToolbar.apply {
            title = "Agregar tarea"
            setNavigationIcon(R.drawable.ic_back_button)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.overviewScreen)
            }
        }

        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(AddTaskViewModel::class.java)

        addTaskButton.setOnClickListener { insertNewTask() }
    }

    private fun insertNewTask() {
        taskName = newTaskName.editText?.text.toString()
        taskDescription = newTaskDescription.editText?.text.toString()
        if (taskFavorite.isChecked) {
            taskFav = true
        }

        if (taskName.isNotEmpty() && taskAuthor.isNotEmpty()) {
            if (viewModel.addTask(taskName, taskDescription, taskFav, taskAuthor)) {
                findNavController().navigate(R.id.overviewScreen)
            } else {
                showErrorDialog(getString(R.string.taskNameError))
            }
        } else {
            showErrorDialog(getString(R.string.addTaskError))
        }

    }

    private fun showErrorDialog(errorMessage: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.errorTitle))
        builder.setMessage(errorMessage)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        builder.show()
    }

}