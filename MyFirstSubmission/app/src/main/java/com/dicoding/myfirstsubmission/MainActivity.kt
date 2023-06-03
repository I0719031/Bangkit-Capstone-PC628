package com.dicoding.myfirstsubmission

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myfirstsubmission.adapter.UsersAdapter
import com.dicoding.myfirstsubmission.data.DataUser
import com.dicoding.myfirstsubmission.databinding.ActivityMainBinding
import com.dicoding.myfirstsubmission.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter : UsersAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnitemClikCallback(object : UsersAdapter.OnItemClickCallback{
            override fun onItemClicked(data: DataUser) {
                Toast.makeText(this@MainActivity, "Opening "+data.login + " profile", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                startActivity(intent)
            }
        })

        binding.apply {
            rvReview.layoutManager = LinearLayoutManager(this@MainActivity)
            rvReview.setHasFixedSize(true)
            rvReview.adapter=adapter

            btnSearch.setOnClickListener {
                searchUser()
            }

            etQuery.setOnKeyListener {view, i, keyEvent ->
                if(keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        mainViewModel.listUser.observe(this){
            if(it != null){
                adapter.setList(it)
                showLoading(false)
            }
        }
        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun searchUser(){
        binding.apply {
            val query = etQuery.text.toString()
            if(query.isEmpty()) return
            showLoading(true)
            mainViewModel.SearchGithubUser(query)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}