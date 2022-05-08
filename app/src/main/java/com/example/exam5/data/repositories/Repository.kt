package com.example.exam5.data.repositories

import com.example.exam5.data.Local.LocalDataSource
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.Resource
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getlocaluser() = localDataSource.getLocalUsers()
    suspend fun insertUser(user: User) = localDataSource.insertUser(user)
    suspend fun deletUser(user: User) = localDataSource.deletUser(user)
    suspend fun updateUser(user: User) = localDataSource.updateUser(user)

    fun getUserList(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading())

            try {
                val response = remoteDataSource.getUserList()
                emit(Resource.Success(response))

            } catch (e: Exception) {
                emit(Resource.Error(throwable = Throwable("Warning")))
            }


        }
    }


    suspend fun showInfo(id: String) = remoteDataSource.showInfo(id)


    suspend fun uploadimage(id: String, image: MultipartBody.Part) =
        remoteDataSource.uploadimage(id, image)

    suspend fun creatuser(user: CreateUser) = remoteDataSource.creatuser(user)
}