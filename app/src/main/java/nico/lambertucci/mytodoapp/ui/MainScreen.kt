package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_screen_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.adapter.TaskAdapter
import nico.lambertucci.mytodoapp.ui.viewmodel.MainScreenViewModel

class MainScreen : Fragment() {


    private lateinit var viewModel: MainScreenViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        setupBottomNavigation()
        setUpView()
    }

    private fun setUpView() {
        viewManager = LinearLayoutManager(requireContext())
        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            viewAdapter = TaskAdapter(it)
            recyclerView = requireView().findViewById<RecyclerView>(R.id.mainTaskRecyclerView).apply {
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
                layoutManager = viewManager
                adapter = viewAdapter
            }
        })

    }

    private fun setupBottomNavigation() {
        bottomNavigationView.selectedItemId =
            R.id.overview
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.overview -> {
                    //TODO NAVIGATE TO
                    true
                }
                R.id.addNewTask -> {
                    //TODO NAVIGATE TO ADD TASK
                    true
                }
                R.id.favs -> {
                    //TODO NAVIGATE TO FAVORITES
                    true
                }
                else -> false
            }
        }
    }

}