package com.example.exam5.ui.mainactivity

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class Myviewmodel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private val _usrid = MutableLiveData<String>()
        val usrid: LiveData<String> = _usrid
    fun creatUser(user: CreateUser) {
        viewModelScope.launch {
            val response = repository.creatuser(user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _usrid.postValue(response.body())
                }
            }
        }
    }


}