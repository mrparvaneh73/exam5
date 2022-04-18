package com.example.exam5.ui.updatefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.exam5.R
import com.example.exam5.databinding.FragmentUpdateBinding
import com.example.exam5.model.CreateUser
import com.example.exam5.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment: Fragment(R.layout.fragment_update) {
    private var _binding:FragmentUpdateBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UpadateViewModel>()
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentUpdateBinding.bind(view)
        binding.nationalcodeEt.setText(args.currentUser.nationalCode)
        binding.firstnameEt.setText(args.currentUser.firstName)
        binding.lastnameEt.setText(args.currentUser.lastName)
        binding.tvSave.setOnClickListener {
            viewModel.updateUser(
                User(
                    _id=args.currentUser._id,
                   firstName= binding.firstnameEt.text.toString(),
                    lastName = binding.lastnameEt.text.toString(),
                    nationalCode = binding.nationalcodeEt.text.toString()

            )
            )
            viewModel.creatUser(CreateUser(
                firstName= binding.firstnameEt.text.toString(),
                lastName = binding.lastnameEt.text.toString(),
                nationalCode = binding.nationalcodeEt.text.toString()
            ))
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToLocalUserFragment())
        }
        binding.tvCancel.setOnClickListener {
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToLocalUserFragment())
        }
    }

}