package com.example.exam5.ui.LocalUserFragment

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
import com.example.exam5.databinding.FragmentLocaluserBinding
import com.example.exam5.model.User
import com.example.exam5.ui.homefragment.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocalUserFragment : Fragment(R.layout.fragment_localuser) {
    private var _binding: FragmentLocaluserBinding? = null
    private val binding get() = _binding!!
    private var list: MutableList<User> = mutableListOf()
    private val viewModel by viewModels<LocalUserViewModel>()
    var adapter = UserAdapter(showDetails = { user ->
        findNavController().navigate(
            LocalUserFragmentDirections.actionLocalUserFragmentToUpdateFragment(
                user
            )
        )

    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLocaluserBinding.bind(view)

        observers()

        swipe()

        binding.recyclerView.adapter = adapter
    }


    private fun swipe()=binding.apply {
        val swipGesture = object : SwipGestureDelete(view?.context!!) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val position = viewHolder.absoluteAdapterPosition
                        viewModel.deletUser(list[position])
                        list.removeAt(position)
                        recyclerView.adapter!!.notifyItemRemoved(position)
                        recyclerView.adapter!!.notifyItemRangeChanged(position, list.size)
                        adapter.submitList(list)

                    }
                    ItemTouchHelper.RIGHT -> {
                        val position = viewHolder.absoluteAdapterPosition
                        findNavController().navigate(
                            LocalUserFragmentDirections.actionLocalUserFragmentToUpdateFragment(
                                list[position]
                            )
                        )

                    }

                }

            }
        }
        val touchHelper = ItemTouchHelper(swipGesture)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    private fun observers() = binding.apply {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    adapter.submitList(it)
                    list.addAll(it)

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}