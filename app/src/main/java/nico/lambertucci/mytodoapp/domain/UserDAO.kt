package nico.lambertucci.mytodoapp.domain

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDAO {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE user.`username` = :username")
    fun verifyUserById(username: String): User

   /* @Query("SELECT * FROM user WHERE user.`username` = :username")
    fun getUserById(username: String): LiveData<Boolean>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun delete(user: User)

}