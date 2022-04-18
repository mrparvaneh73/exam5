package com.example.exam5.data.repositories

import com.example.exam5.data.Local.LocalDataSource
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.Resource
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getlocaluser()=localDataSource.getLocalUsers()
    suspend fun insertUser(user: User) = localDataSource.insertUser(user)
    suspend fun deletUser(user: User)=localDataSource.deletUser(user)
    suspend fun updateUser(user:User)=localDataSource.updateUser(user)

    suspend fun getUserList(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading())
            val response = remoteDataSource.getUSerList()
            if (response.isSuccessful) {
                response.body()?.let { users ->
                    emit(Resource.Success(users))
                }
            } else {
                emit(Resource.Error(throwable = Throwable("Something went wrong!")))
            }

        }
    }



    suspend fun showInfo(id: String) = remoteDataSource.showInfo(id)

    suspend fun uploadimage(id: String, image: MultipartBody.Part) =
        remoteDataSource.uploadimage(id, image)

    suspend fun creatuser(user: CreateUser) = remoteDataSource.creatuser(user)
}