package com.example.exam5.ui.detailsfragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exam5.data.Repository
import com.example.exam5.ui.homefragment.HomeViewModel

class DetailViewModelFactory(
    private  val repository: Repository,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}