package nico.lambertucci.mytodoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import nico.lambertucci.mytodoapp.R
import nico.lambertucci.mytodoapp.domain.database.TaskDatabase

 lateinit var taskDatabase: TaskDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskDatabase = TaskDatabase.getDatabase(applicationContext)

        val host = NavHostFragment.create(R.navigation.nav_graph)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    host
                )
                .setPrimaryNavigationFragment(host)
                .commitNow()
        }
    }
}