package com.dicoding.lecker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.lecker.adapter.SectionPageAdapter
import com.dicoding.lecker.databinding.ActivityDetailUserBinding
import com.dicoding.lecker.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TOMBOL BACK
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        if (username != null) {
            detailUserViewModel.setUserDetail(username)
        }

        showLoading(true)
        detailUserViewModel.user.observe(this){
            if(it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUserName.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Followings"

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(imageView)
                }
            }
            showLoading(false)
        }

        detailUserViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val sectionPagerAdapters = SectionPageAdapter(this, bundle)
        binding.viewPager.adapter = sectionPagerAdapters

        TabLayoutMediator(binding.tabs,binding.viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }



    private fun ShareApp() {
        val appMsg :String = "Check this out :- "+
                "https://github.com/android/com.dicoding.myfirstsubmission"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, appMsg)
        intent.type = "text/plain"
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility =
            if (state) View.VISIBLE
            else View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share -> ShareApp()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        private val TAB_TITLES = intArrayOf(
            R.string.follower_label,
            R.string.following_label
        )
    }
}