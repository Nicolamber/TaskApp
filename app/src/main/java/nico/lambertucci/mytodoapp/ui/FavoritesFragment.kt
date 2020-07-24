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
import kotlinx.android.synthetic.main.favorites_fragment.*
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.ui.adapter.FavoritesAdapter
import nico.lambertucci.mytodoapp.ui.viewmodel.FavoritesViewModel
import nico.lambertucci.mytodoapp.utils.FavItemListener

class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var favRecyclerView: RecyclerView
    private lateinit var favViewAdapter: RecyclerView.Adapter<*>
    private lateinit var favViewManager: RecyclerView.LayoutManager
    private lateinit var favsToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorites_fragment, container, false)

        favsToolbar = view.findViewById(R.id.overviewToolbar)
        (activity as AppCompatActivity).setSupportActionBar(favsToolbar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        favsToolbar.apply {
            title = "Tus tareas destacadas!"
            setNavigationIcon(R.drawable.ic_back_button)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.overviewScreen)
            }
        }

        setUpView()
        setupBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        setUpView()
    }

    private fun setUpView() {
        favViewManager = LinearLayoutManager(requireContext())
        viewModel.getFavorites().observe(viewLifecycleOwner, Observer {
            favViewAdapter = FavoritesAdapter(it, object : FavItemListener {
                override fun onClick(position: Int,taskId: Int) {
                    val bundle: Bundle? = Bundle()
                    bundle?.putInt("taskId",taskId)
                    findNavController().navigate(R.id.detailFragment,bundle)
                }

            })
            favRecyclerView =
                requireView().findViewById<RecyclerView>(R.id.favTaskRecyclerView).apply {
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    layoutManager = favViewManager
                    adapter = favViewAdapter
                }
        })

    }

    private fun setupBottomNavigation() {

        favBottomNavigationView.selectedItemId =
            R.id.favs

        favBottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.overview -> {
                    findNavController().navigate(R.id.overviewScreen)
                    true
                }
                R.id.addNewTask -> {
                    findNavController().navigate(R.id.addTask)
                    true
                }
                R.id.favs -> {
                    true
                }
                else -> false
            }
        }
    }
}