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
import nico.lambertucci.mytodoapp.ui.viewmodel.AddTaskViewModel


class AddTaskFragment : Fragment() {

    companion object {
        fun newInstance() = AddTaskFragment()
    }

    private lateinit var viewModel: AddTaskViewModel
    private lateinit var taskName: String
    private var taskFav: Boolean = false
    private var taskDescription: String? = null
    private var author: String? = null
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
        if (arguments != null) {
            author = requireArguments().getString("taskAuthor")
        }

        newTaskToolbar.apply {
            title = "Agregar tarea"
            //TODO Modify navigate back with args
           /* setNavigationIcon(R.drawable.ic_back_button)
            setNavigationOnClickListener {
                val bundle: Bundle? = Bundle()
                bundle?.putString("taskAuthor", author)
                findNavController().navigate(R.id.overviewScreen)
            }*/
        }

        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)

        addTaskButton.setOnClickListener { insertNewTask() }
    }

    private fun insertNewTask() {
        taskName = newTaskName.editText?.text.toString()
        taskDescription = newTaskDescription.editText?.toString()
        if (taskFavorite.isChecked) {
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
        findNavController().navigate(R.id.overviewScreen)
    }

}