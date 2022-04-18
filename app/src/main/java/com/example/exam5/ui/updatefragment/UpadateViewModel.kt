package com.example.exam5.ui.updatefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpadateViewModel @Inject constructor(private val repository: Repository):ViewModel() {
    fun updateUser(user: User)=viewModelScope.launch {
        repository.updateUser(user)
    }
    fun creatUser(user: CreateUser) = viewModelScope.launch {
       repository.creatuser(user)
        }

}