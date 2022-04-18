package com.example.exam5.data.Local

import com.example.exam5.model.User
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(user: User)=userDao.insertUser(user)
    fun getLocalUsers() = userDao.getUsers()
    suspend fun deletUser(user: User)=userDao.deletUser(user)
    suspend fun updateUser(user:User)=userDao.updateUser(user)
}