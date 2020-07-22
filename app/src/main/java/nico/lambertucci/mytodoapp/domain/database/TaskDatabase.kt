package nico.lambertucci.mytodoapp.domain.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nico.lambertucci.mytodoapp.domain.TaskDAO
import nico.lambertucci.mytodoapp.domain.UserDAO

@Database(
    version = 1,
    exportSchema = false,
    entities = [Task::class, User::class]
)
abstract class TaskDatabase : RoomDatabase() {
    companion object {
        private val TAG = "Database"
        private lateinit var databaseInstance: TaskDatabase

        fun getDatabase(context: Context): TaskDatabase {
            if (!this::databaseInstance.isInitialized) {
                synchronized(this) {
                    databaseInstance = Room.databaseBuilder(
                        context,
                        TaskDatabase::class.java,
                        "task-database"
                    )
                        .allowMainThreadQueries()
                        .build()

                    Log.i(TAG, "Database created!")
                    return databaseInstance
                }
            } else {
                Log.e(TAG, "An error has occurred!")
                return databaseInstance
            }
        }
    }

    abstract fun taskDAO(): TaskDAO
    abstract fun userDAO(): UserDAO
}