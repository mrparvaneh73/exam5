package com.example.exam5.ui.homefragment

import android.app.Application
import androidx.lifecycle.*
import com.example.exam5.data.remote.Resource
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    private val _users = MutableStateFlow<Resource<List<User>>>(Resource.Success(emptyList()))
    val users: StateFlow<Resource<List<User>>>
        get() = _users

init {
    getUserList()
}

        fun getUserList()= viewModelScope.launch {
            repository.getUserList().collect{
                _users.value=it
            }
        }

    fun insertPerson(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

fun ViewModel.launchViewModelScope(){
    viewModelScope.launch {

    }
}

}