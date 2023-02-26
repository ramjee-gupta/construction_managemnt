package com.example.shopingdemo.auth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopingdemo.R
import com.example.shopingdemo.databinding.ActivitySelectAppLanguageBinding
import com.example.shopingdemo.databinding.ActivitySelectUserRoleBinding

class SelectUserRoleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectUserRoleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectUserRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {

        binding.apply {

            textViewSignIn.setOnClickListener {
                startActivity(Intent(this@SelectUserRoleActivity, SignInActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
            englishLanguage.setOnClickListener {

                startActivity(Intent(this@SelectUserRoleActivity, SignUpActivity::class.java)
                    .putExtra("language", intent.getStringExtra("language"))
                    .putExtra("role","labor")
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
            hindiLanguage.setOnClickListener {
                startActivity(Intent(this@SelectUserRoleActivity, SignUpActivity::class.java)
                    .putExtra("language", intent.getStringExtra("language"))
                    .putExtra("role","contractor")
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()


            }
            otherLanguage.setOnClickListener {
                startActivity(Intent(this@SelectUserRoleActivity, SignUpActivity::class.java)
                    .putExtra("language", intent.getStringExtra("language"))
                    .putExtra("role","developer")
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }
    }
}