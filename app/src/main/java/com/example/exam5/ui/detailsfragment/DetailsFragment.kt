package com.example.exam5.ui.detailsfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.exam5.R
import com.example.exam5.data.Repository
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.network.Service
import com.example.exam5.databinding.FragmentDetailsBinding
import com.example.exam5.ui.homefragment.HomeViewModel
import com.example.exam5.ui.homefragment.HomeViewModelFactory

class DetailsFragment:Fragment(R.layout.fragment_details) {
    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var myviewModel:DetailViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentDetailsBinding.bind(view)
        viewmodelprovider()
        myviewModel.showinfo(args.userid)
        myviewModel.userList.observe(viewLifecycleOwner){
            binding.firstname.text=it.firstName
            binding.lastname.text=it.lastName
            binding.nationalcode.text=it.nationalCode
        }
    }



    private fun viewmodelprovider() {
        val application = requireNotNull(this.activity).application

        val remoteDataSource = RemoteDataSource(Service)

        val repository = Repository(remoteDataSource)

        val factory = DetailViewModelFactory(repository, application)

        myviewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }
}