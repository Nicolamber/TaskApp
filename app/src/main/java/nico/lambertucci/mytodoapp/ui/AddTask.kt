package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.add_task_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.viewmodel.AddTaskViewModel

class AddTask : Fragment() {

    companion object {
        fun newInstance() = AddTask()
    }

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var taskName: String
    private  var taskFav: Boolean = false
    private var taskDescription: String? = null
    private var author: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments !=null){
            author = requireArguments().getString("taskAuthor")
        }
        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)

        addTaskButton.setOnClickListener { insertNewTask() }
    }

    private fun insertNewTask() {
        taskName = newTaskName.editText?.text.toString()
        taskDescription = newTaskDescription.editText?.toString()
        if (taskFavorite.isChecked){
            taskFav = true
        }

        if (taskName.isNotEmpty()) {
            viewModel.addTask(taskName, taskDescription, taskFav, author)
        } else {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Error!")
            builder.setMessage("Ocurrió un error al guardar la Tarea, por favor vuelve a intentarlo")
            builder.setPositiveButton(android.R.string.ok) { _, _ ->
                newTaskName.editText?.text = null
                newTaskDescription.editText?.text = null
            }
            builder.show()
        }
        findNavController().navigate(R.id.mainScreen)
    }

}