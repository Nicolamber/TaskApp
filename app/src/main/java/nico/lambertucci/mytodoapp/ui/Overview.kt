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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.overview_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.adapter.TaskAdapter
import nico.lambertucci.mytodoapp.ui.viewmodel.OverviewViewModel
import nico.lambertucci.mytodoapp.utils.FavItemListener

class Overview : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var overviewToolbar: androidx.appcompat.widget.Toolbar
    private var username: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.overview_fragment, container, false)

        overviewToolbar = view.findViewById(R.id.overviewToolbar)
        (activity as AppCompatActivity).setSupportActionBar(overviewToolbar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
        if (arguments != null) {
            username = requireArguments().getString("taskAuthor")

        }
        overviewToolbar.apply {
            title = "Inicio"
            subtitle = "Bienvenido $username!"

        }
        setupBottomNavigation()
        setUpView()
    }

    override fun onResume() {
        super.onResume()
        if (arguments != null) {
            username = requireArguments().getString("taskAuthor")

        }
        overviewToolbar.apply {
            title = "Inicio"
            subtitle = "Bienvenido $username!"

        }
        setupBottomNavigation()
        setUpView()
    }

    private fun setUpView() {
        viewManager = LinearLayoutManager(requireContext())
        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            viewAdapter = TaskAdapter(it, object : FavItemListener {
                override fun onClick(position: Int) {
                    findNavController().navigate(R.id.addTask)
                }

            })
            recyclerView =
                requireView().findViewById<RecyclerView>(R.id.mainTaskRecyclerView).apply {
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
        })

    }

    private fun setupBottomNavigation() {

        val bundle: Bundle? = Bundle()

        bottomNavigationView.selectedItemId =
            R.id.overview

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.overview -> {
                    true
                }
                R.id.addNewTask -> {
                    bundle?.putString("taskAuthor", username)
                    findNavController().navigate(R.id.addTask, bundle)
                    true
                }
                R.id.favs -> {
                    bundle?.putString("taskAuthor", username)
                    findNavController().navigate(R.id.favoritesFragment, bundle)
                    true
                }
                else -> false
            }
        }
    }

}