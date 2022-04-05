package com.example.exam5.data

import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.model.User
import okhttp3.MultipartBody

class Repository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getUserList()=remoteDataSource.getUSerList()
    suspend fun showInfo(id: String)=remoteDataSource.showInfo(id)
    suspend fun uploadimage(id: String, image: MultipartBody.Part)=remoteDataSource.uploadimage(id,image)
    suspend fun creatuser(user: User)=remoteDataSource.creatuser(user)
}