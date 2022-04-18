package com.example.exam5.ui.LocalUserFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam5.data.repositories.Repository
import com.example.exam5.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalUserViewModel @Inject constructor(private val repository: Repository):ViewModel()  {

    val users = repository.getlocaluser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )
    fun deletUser(user: User)=viewModelScope.launch {
        repository.deletUser(user)
    }


}