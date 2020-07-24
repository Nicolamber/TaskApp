package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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

        setupToolbar()
        setupBottomNavigation()
        setUpView()
    }

    override fun onResume() {
        super.onResume()

        setupBottomNavigation()
        setupToolbar()
        setUpView()
    }

    private fun setUpView() {
        viewManager = LinearLayoutManager(requireContext())
        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            viewAdapter = TaskAdapter(it, object : FavItemListener {
                override fun onClick(position: Int,taskId: Int) {
                    val bundle: Bundle? = Bundle()
                    bundle?.putInt("taskId",taskId)
                    findNavController().navigate(R.id.detailFragment,bundle)
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

        bottomNavigationView.selectedItemId =
            R.id.overview

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.overview -> {
                    true
                }
                R.id.addNewTask -> {
                    findNavController().navigate(R.id.addTask)
                    true
                }
                R.id.favs -> {
                    findNavController().navigate(R.id.favoritesFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupToolbar(){
        overviewToolbar.apply {
            title = "Inicio"
            subtitle = "Bienvenido: $taskAuthor"
            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.CloseSession ->{ activity?.finish()}
        }
        return super.onOptionsItemSelected(item)
    }
}