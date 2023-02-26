package com.example.shopingdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shopingdemo.auth.ui.SignInActivity
import com.example.shopingdemo.auth.ui.SignInViewModel
import com.example.shopingdemo.dashboard.ui.DashboardActivity
import com.example.shopingdemo.databinding.ActivityMainBinding
import com.example.shopingdemo.server.retrofit.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: SignInViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpObserver()
        viewModel.getUser()
    }

    private fun setUpObserver() {

        viewModel.alreadyLogin.observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if ((it.data ?: 0) > 0) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                    } else
                        startActivity(Intent(this@MainActivity, SignInActivity::class.java))

                    finish()
                }
            }
        }
    }

}