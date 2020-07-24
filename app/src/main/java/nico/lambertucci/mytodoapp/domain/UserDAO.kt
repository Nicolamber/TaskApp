package nico.lambertucci.mytodoapp.domain

import androidx.room.*
import nico.lambertucci.mytodoapp.domain.database.User


@Dao
interface UserDAO {
    
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE user.`username` = :username")
    fun verifyUserById(username: String): User?

   /* @Query("SELECT * FROM user WHERE user.`username` = :username")
    fun getUserById(username: String): LiveData<Boolean>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

}