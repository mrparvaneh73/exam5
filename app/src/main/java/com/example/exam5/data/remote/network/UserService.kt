package com.example.exam5.data.remote.network

import androidx.room.Insert
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import com.example.exam5.model.UserInfo
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @Multipart
    @POST("users/{id}/image")
    suspend fun sendImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Response<Any>

    @GET("http://papp.ir/api/v1/users/{id}")
    suspend fun getShowInfo(@Path("id") id: String):Response<User>

    @POST("users")
    suspend fun creatUser(@Body user: CreateUser): Response<String>
}