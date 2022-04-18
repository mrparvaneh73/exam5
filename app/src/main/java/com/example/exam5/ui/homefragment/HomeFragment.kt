package com.example.exam5.ui.homefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.UserAdapter
import com.example.exam5.R
import com.example.exam5.SwipGesture
import com.example.exam5.data.remote.Resource
import com.example.exam5.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val myviewModel by viewModels<HomeViewModel>()

    val adapter = UserAdapter(showDetails = { user ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                    user._id
                )
            )
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)


        observer()
        swipe()
       binding.recyclerView.adapter = adapter


    }

    fun observer() = binding.apply {
        myviewModel.getUserList()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                myviewModel.users.collectLatest {
                    when (it) {
                        is Resource.Success -> {
                            adapter.submitList(it.data)
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {

                        }
                    }

                }
            }
        }
    }

    fun swipe() = binding.apply {
        val swipGesture = object : SwipGesture(view?.context!!) {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val position = viewHolder.absoluteAdapterPosition

                        lifecycleScope.launch {
                            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                myviewModel.users.collectLatest {
                                    myviewModel.insertPerson(it.data?.get(position)!!)
                                }
                            }
                        }


                        recyclerView.adapter?.notifyDataSetChanged()

                    }
                    ItemTouchHelper.RIGHT -> {
                        val position = viewHolder.absoluteAdapterPosition
                        lifecycleScope.launch {
                            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                myviewModel.users.collectLatest {
                                    findNavController().navigate(
                                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                            it.data?.get(position)!!._id
                                        )
                                    )
                                }
                            }
                        }


                    }

                }

            }
        }
        val touchHelper = ItemTouchHelper(swipGesture)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}