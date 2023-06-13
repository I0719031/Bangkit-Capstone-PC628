package com.dicoding.lecker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.lecker.data.DataUser
import com.dicoding.lecker.databinding.ItemUserBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    private val listUser = ArrayList<DataUser>()
    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ViewHolder(var binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user : DataUser){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                tvItemName.text = user.login
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(circleImageView)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataUser)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<DataUser>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    fun setOnitemClikCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}