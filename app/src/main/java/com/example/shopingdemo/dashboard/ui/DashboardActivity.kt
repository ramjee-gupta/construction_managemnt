package com.example.shopingdemo.dashboard.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.shopingdemo.R
import com.example.shopingdemo.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var adapter: DashBoardPageAdapter
    lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
    }

    private fun setUpUi() {
        adapter = DashBoardPageAdapter(
            supportFragmentManager,
            lifecycle
        )

        binding.apply {

            bottomNav.setOnNavigationItemSelectedListener(this@DashboardActivity)

            viewPager2.isUserInputEnabled = false
            setupViewPager(binding.viewPager2)
        }
    }

    private fun setupViewPager(viewPager2: ViewPager2) {

        adapter.addFragment(DashboardFragment())
        adapter.addFragment(ProfileFragment())
        viewPager2.adapter = adapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashboard -> {
                binding.viewPager2.currentItem = 0
                return true
            }
            R.id.profile -> {
                binding.viewPager2.currentItem = 1
                return true
            }
        }
        return false
    }

}