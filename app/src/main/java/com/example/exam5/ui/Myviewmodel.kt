package com.example.exam5.ui

import android.app.Application
import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.exam5.data.Repository
import com.example.exam5.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Myviewmodel(private val repository: Repository, application: Application) :
    AndroidViewModel(application) {
    private val _usrid = MutableLiveData<String>()
        val usrid: LiveData<String> = _usrid
    fun addUser(user: User) {
        viewModelScope.launch {
            val response = repository.creatuser(user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _usrid.postValue(response.body())
                    Log.d("issuccecfull", "addUser: " + response.body())
                }
            }
        }
    }


}