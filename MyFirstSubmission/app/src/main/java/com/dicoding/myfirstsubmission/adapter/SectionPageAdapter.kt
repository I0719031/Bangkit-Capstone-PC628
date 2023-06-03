package com.dicoding.myfirstsubmission.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.myfirstsubmission.fragment.FollowersFragment
import com.dicoding.myfirstsubmission.fragment.FollowingFragment

class SectionPageAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity)  {
    private var fragmentBundleUser: Bundle
    init{
        fragmentBundleUser = data
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        fragment?.arguments = this.fragmentBundleUser
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}