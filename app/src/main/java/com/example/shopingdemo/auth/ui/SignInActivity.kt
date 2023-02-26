package com.example.shopingdemo.auth.ui

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import com.example.shopingdemo.dashboard.ui.DashboardActivity
import com.example.shopingdemo.databinding.ActivitySignInBinding
import com.example.shopingdemo.server.retrofit.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private val viewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObserver()
    }

    private fun setUpObserver() {

        viewModel.signIn.observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.root.visibility = GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.root.visibility = VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.root.visibility = GONE
                    if (it.data != null) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignInActivity, DashboardActivity::class.java))
                    }
                    else
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun setUpUI() {


        binding.apply {
            textViewSignUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SelectAppLanguageActivity::class.java))
                finish()
            }

            textViewProceed.setOnClickListener {
                if (validDetails()) {
                    viewModel.signIn(
                        email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString()
                    )
                } else {
                    Toast.makeText(this@SignInActivity, "Enter valid login details", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun validDetails(): Boolean {
        return binding.editTextEmail.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()
    }
}