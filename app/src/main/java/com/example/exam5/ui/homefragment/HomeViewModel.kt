package com.example.exam5.ui.homefragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam5.data.Repository
import com.example.exam5.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {
    private val _userList = MutableLiveData<List<User>>()
        val userList: LiveData<List<User>> = _userList
    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUserList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                   _userList.postValue(response.body())
                }
            }
        }
    }


}