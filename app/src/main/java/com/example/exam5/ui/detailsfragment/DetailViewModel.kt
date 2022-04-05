package com.example.exam5.ui.detailsfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam5.data.Repository
import com.example.exam5.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val repository: Repository, application: Application) :
    AndroidViewModel(application){
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
}