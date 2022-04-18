package com.example.exam5.ui.LocalUserFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exam5.databinding.UserItemBinding
import com.example.exam5.model.User

class LocalAdapter(private var showDetails: (User) -> Unit) :
    ListAdapter<User, LocalAdapter.UserListViewHolder>(TodoDiffCallback()) {


    class UserListViewHolder(
        private val binding: UserItemBinding,
        private var showDetails: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var firstname = binding.firstname
        private var lastname = binding.lastname


        fun bind(user: User) {
            firstname.text = user.firstName
            lastname.text=user.lastName
            binding.root.setOnClickListener {
                showDetails(user)
            }


        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

        return UserListViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            showDetails
        )
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.nationalCode == newItem.nationalCode
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

