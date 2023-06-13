package com.dicoding.lecker.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.lecker.DetailUserActivity
import com.dicoding.lecker.R
import com.dicoding.lecker.adapter.UsersAdapter
import com.dicoding.lecker.databinding.FragmentFollowBinding
import com.dicoding.lecker.viewmodel.FollowViewModel

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding :FragmentFollowBinding?= null
    private val binding get() = _binding!!

    private val viewModelFollow by viewModels<FollowViewModel>()
    private lateinit var adapter : UsersAdapter
    private lateinit var username : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = UsersAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvReview.setHasFixedSize(true)
            rvReview.layoutManager = LinearLayoutManager(activity)
            rvReview.adapter = adapter
        }

        showLoading(true)
        viewModelFollow.setListFollowers(username)
        viewModelFollow.followers.observe(viewLifecycleOwner){
            if(it != null){
                adapter.setList(it)
                showLoading(false)
            }
        }
        viewModelFollow.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility =
            if(state) View.VISIBLE
            else View.GONE
    }
}