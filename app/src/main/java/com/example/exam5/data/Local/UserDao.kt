package com.example.exam5.data.Local

import androidx.room.*
import com.example.exam5.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    @Query("SELECT * FROM USER")
    fun getUsers(): Flow<List<User>>

    @Delete
    suspend fun deletUser(user: User)

    @Update
    suspend fun updateUser(user:User)
}