package nico.lambertucci.mytodoapp.domain

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import nico.lambertucci.mytodoapp.domain.database.TaskDatabase
import nico.lambertucci.mytodoapp.domain.database.User
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserUITest {

    private var database: TaskDatabase
    private var userDao: UserDAO

    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            TaskDatabase::class.java
        ).build()

        userDao = database.userDAO()
    }

    @Test
    fun insertValidUser() {

        val user = User("Jhon", "Terminator2233")
        userDao.insertUser(user)

        val result: User? = userDao.verifyUserById("Jhon")
        assertTrue(result?.username == user.username)
        assertTrue(result?.password == user.password)
    }

    @Test
    fun insertAListOfUsersShouldReturnMoreThanOneResult() {
        val user1 = User("Jhon", "Terminator2233")
        val user2 = User("Richard", "Geere123")
        val user3 = User("Nick", "Elvis12")

        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        val resultList = userDao.getAllUsers()

        assertTrue(resultList.isNotEmpty())
        assertTrue(resultList.size == 3)
    }

    @Test
    fun deleteUserShouldReturnNull() {
        val user = User("Jhon", "Terminator2233")

        userDao.insertUser(user)
        assertTrue(userDao.verifyUserById(user.username) != null)

        userDao.deleteUser(user)
        assertTrue(userDao.verifyUserById(user.username) == null)
    }
}