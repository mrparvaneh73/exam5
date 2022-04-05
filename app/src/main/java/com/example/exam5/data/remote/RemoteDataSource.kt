package com.example.exam5.data.remote

import com.example.exam5.data.remote.network.Service
import com.example.exam5.data.remote.network.UserApi
import com.example.exam5.model.User
import okhttp3.MultipartBody

class RemoteDataSource(private val service: Service) {
    suspend fun getUSerList() = service.service.getuser()


    suspend fun showInfo(id: String) = service.service.getShowInfo(id)


    suspend fun uploadimage(id: String, image: MultipartBody.Part) = service.service.sendImage(id, image)


    suspend fun creatuser(user: User) = service.service.creatUser(user)

}