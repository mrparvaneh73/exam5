package com.example.exam5.ui.detailsfragment

import androidx.lifecycle.*
import com.example.exam5.data.remote.Resource
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) :
    ViewModel(){
    private val _userInfo = MutableSharedFlow<User>()
    val usersInfo: SharedFlow<User>
        get() = _userInfo

    fun showinfo(id:String) {
        viewModelScope.launch {
            val response= repository.showInfo(id)
            if (response.isSuccessful){
                _userInfo.emit(response.body()!!)
            }


//


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