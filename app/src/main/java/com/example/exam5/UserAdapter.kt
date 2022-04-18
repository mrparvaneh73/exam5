package com.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exam5.databinding.UserItemBinding
import com.example.exam5.model.User

class UserAdapter(private var showDetails: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserListViewHolder>(TodoDiffCallback()) {


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
            Glide.with(binding.root).load(user.image).into(binding.imageview)


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