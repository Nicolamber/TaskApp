package nico.lambertucci.mytodoapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import nico.lambertucci.mytodoapp.di.Injection
import nico.lambertucci.mytodoapp.ui.adapter.TaskAdapter
import nico.lambertucci.mytodoapp.ui.viewmodel.OverviewViewModel
import nico.lambertucci.mytodoapp.utils.FavItemListener
import nico.lambertucci.mytodoapp.utils.ToolbarInterface
import androidx.appcompat.widget.Toolbar
import java.lang.NullPointerException

class Overview : Fragment(), ToolbarInterface {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var overviewToolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.overview_fragment, container, false)

        overviewToolbar = view.findViewById(R.id.favoritesToolbar)
        (activity as AppCompatActivity).setSupportActionBar(overviewToolbar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,Injection.getViewModelFactory()).get(OverviewViewModel::class.java)

        setupToolbar(overviewToolbar)
        setupBottomNavigation()
        setUpView()
    }

    override fun onResume() {
        super.onResume()

        setupBottomNavigation()
        setupToolbar(overviewToolbar)
        setUpView()
    }

    private fun setUpView() {
        viewManager = LinearLayoutManager(requireContext())
        try {
        viewModel.getTasks(taskAuthor)?.observe(viewLifecycleOwner, Observer {
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
        }catch (e: NullPointerException){
            val builder = AlertDialog.Builder(requireContext())
            builder.apply {
                setMessage(getString(R.string.noTasks))
                setPositiveButton(android.R.string.ok) { dialog, _ ->
                    findNavController().navigate(R.id.addTask)
                    dialog.dismiss()
                }
                setNegativeButton(android.R.string.cancel){dialog,_ -> dialog.dismiss()}
                show()
            }
        }

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

    override fun setupToolbar(toolbar: Toolbar){
        toolbar.apply {
            title = getString(R.string.overview)
            subtitle = "${getString(R.string.welcome)} $taskAuthor"
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