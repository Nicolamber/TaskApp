package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.detail_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.di.Injection
import nico.lambertucci.mytodoapp.ui.viewmodel.DetailViewModel
import nico.lambertucci.mytodoapp.utils.ToolbarInterface
import androidx.appcompat.widget.Toolbar
import java.lang.NullPointerException

class DetailFragment : Fragment(), ToolbarInterface {

    private lateinit var viewModel: DetailViewModel
    private lateinit var detailToolbar: Toolbar
    private var taskId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        detailToolbar = view.findViewById(R.id.detailTaskToolbar)
        (activity as AppCompatActivity).setSupportActionBar(detailToolbar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            taskId = requireArguments().getInt("taskId")
        }

        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(DetailViewModel::class.java)

        setupToolbar(detailToolbar)
        setupView()

    }

    private fun setupView() {
        try {
        viewModel.getTaskById(taskId)?.observe(viewLifecycleOwner, Observer {
            detailTaskName.text = it.taskName
            detailTaskDescription.text = it.taskDescription
            if (it.isFavorite) {
                detailFavorite.setImageResource(R.drawable.filled_star)
            } else {
                detailFavorite.setImageResource(R.drawable.empty_star)
            }

        })
    }catch (e: NullPointerException){
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setTitle(getString(R.string.errorTitle))
                setMessage(getString(R.string.genericError))
                setPositiveButton(android.R.string.ok) { dialog, _ ->
                    findNavController().navigate(R.id.addTask)
                    dialog.dismiss()
                }
                setNegativeButton(android.R.string.cancel){dialog,_ -> dialog.dismiss()}
                show()
            }
        }
    }

    override fun setupToolbar(toolbar: Toolbar) {
        toolbar.apply {
            title = context.getString(R.string.detailTitle)
            setNavigationIcon(R.drawable.ic_back_button)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.overviewScreen)
            }
        }

    }
}