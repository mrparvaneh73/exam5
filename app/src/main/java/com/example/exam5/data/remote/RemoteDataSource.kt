package com.example.exam5.data.remote

import com.example.exam5.data.remote.network.UserService
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: UserService) {
  suspend fun getUserList():List<User>{
      return service.getUsers()
  }


    suspend fun showInfo(id: String) = service.getShowInfo(id)


    suspend fun uploadimage(id: String, image: MultipartBody.Part) = service.sendImage(id, image)


    suspend fun creatuser(user: CreateUser) = service.creatUser(user)

}