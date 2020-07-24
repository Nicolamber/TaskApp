package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.detail_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var detailToolbar: androidx.appcompat.widget.Toolbar
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
        detailToolbar.apply {
            title = "Detalle"
            setNavigationIcon(R.drawable.ic_back_button)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.overviewScreen)
            }
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        setupView()

    }

    private fun setupView(){
        viewModel.getTaskById(taskId).observe(viewLifecycleOwner, Observer {
            detailTaskName.text = it.taskName
            detailTaskDescription.text = it.taskDescription
            if (it.isFavorite){
                detailFavorite.setImageResource(R.drawable.filled_star)
            }else{
                detailFavorite.setImageResource(R.drawable.empty_star)
            }

        })
    }

}