package com.example.megaapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.os.Bundle


class TabAdapter internal constructor(
    fm: FragmentManager?,
    private val mNumOfTabs: Int
) :
    FragmentStatePagerAdapter(fm!!) {
    // get the current item with position number
    override fun getItem(position: Int): Fragment {
        val b = Bundle()
        b.putInt("position", position+1)
        val frag: Fragment = TabFragment.newInstance()
        frag.arguments = b
        return frag
    }

    // get total number of tabs
    override fun getCount(): Int {
        return mNumOfTabs
    }
}