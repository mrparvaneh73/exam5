package com.example.exam5.ui.detailsfragment

import android.app.Application
import androidx.lifecycle.*
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) :
    ViewModel(){

    private val _userList = MutableLiveData<User>()
    val userList: LiveData<User> = _userList
    fun showinfo(id:String) {
        viewModelScope.launch {
            val response = repository.showInfo(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _userList.postValue(response.body())
                }
            }
        }
    }
    fun uploadimage(id: String, image:ByteArray){
        val body= MultipartBody.create(MediaType.parse("image/*"),image)
        val request = MultipartBody.Part.createFormData("image","imag.jpg",body)
        viewModelScope.launch {
            repository.uploadimage(id,request)
        }

    }

}