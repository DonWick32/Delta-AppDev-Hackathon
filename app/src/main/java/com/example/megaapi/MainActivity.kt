package com.example.megaapi

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var mTabLayout: TabLayout
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        if (id == R.id.requestBtn) {
            page ++
            setTabFragmentToTabLayout(page)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {

        // initialise the layout
        viewPager = findViewById(R.id.viewpager)
        mTabLayout = findViewById(R.id.tabs)

        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
        viewPager.offscreenPageLimit = 5
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // setCurrentItem as the tab position
                viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        setTabFragmentToTabLayout(page)
        viewPager!!.currentItem = 0
    }

    // show all the tab using DynamicFragmnetAdapter
    private fun setTabFragmentToTabLayout(page: Int) {
        // here we have given 10 as the tab number
        // you can give any number here
        /*var L :List<TabLayout.Tab> = listOf()
        for (i in 1..page-1) {
            L += mTabLayout.getTabAt(i)
        }
        L += mTabLayout!!.newTab().setText("Request - $page")
        mTabLayout.removeAllTabs()*/
        mTabLayout!!.addTab(mTabLayout!!.newTab().setText("Request - $page"))

        val mDynamicFragmentAdapter = TabAdapter(
            supportFragmentManager, mTabLayout!!.tabCount
        )

        // set the adapter
        viewPager!!.adapter = mDynamicFragmentAdapter

        // set the current item as 0 (when app opens for first time)

    }
}