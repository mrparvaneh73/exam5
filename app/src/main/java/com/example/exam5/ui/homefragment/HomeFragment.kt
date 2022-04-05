package com.example.exam5.ui.homefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.UserAdapter
import com.example.exam5.R
import com.example.exam5.data.Repository
import com.example.exam5.data.remote.RemoteDataSource
import com.example.exam5.data.remote.network.Service
import com.example.exam5.databinding.FragmentHomeBinding
import com.example.exam5.ui.Myviewmodel
import com.example.exam5.ui.MyViewModelFactory

class HomeFragment:Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myviewModel:HomeViewModel
    val adapter = UserAdapter(
       showDetails = { user ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(user._id
                )
            )
        }
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentHomeBinding.bind(view)
        viewmodelprovider()
        val recyclerView = binding.recyclerView
        myviewModel.getUser()
        myviewModel.userList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            recyclerView.adapter=adapter
        }
    }
    private fun viewmodelprovider() {
        val application = requireNotNull(this.activity).application

        val remoteDataSource = RemoteDataSource(Service)

        val repository = Repository(remoteDataSource)

        val factory = HomeViewModelFactory(repository, application)

        myviewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

}