package com.example.shopingdemo.auth.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.ui.AppBarConfiguration
import com.example.shopingdemo.R
import com.example.shopingdemo.databinding.ActivitySelectAppLanguageBinding

class SelectAppLanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectAppLanguageBinding
    var selectedLanguage = "english"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectAppLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

    }

    private fun setupUI() {

        binding.apply {

            textViewProceed.setOnClickListener {
                startActivity(Intent(this@SelectAppLanguageActivity, SelectUserRoleActivity::class.java).putExtra("language", selectedLanguage))
            }
            englishLanguage.setOnClickListener {
                applyTint(englishLanguage, R.color.primary, R.color.white)
                applyTint(hindiLanguage, R.color.color_CECECE, R.color.color_3B3B3B)
                applyTint(otherLanguage, R.color.color_CECECE, R.color.color_3B3B3B)

                applyTint(textViewProceed, R.color.primary, R.color.white)
                textViewProceed.isEnabled = true
                selectedLanguage = "english"

            }
            hindiLanguage.setOnClickListener {
                applyTint(hindiLanguage, R.color.primary, R.color.white)
                applyTint(englishLanguage, R.color.color_CECECE, R.color.color_3B3B3B)
                applyTint(otherLanguage, R.color.color_CECECE, R.color.color_3B3B3B)
                textViewProceed.isEnabled = true
                applyTint(textViewProceed, R.color.primary, R.color.white)

                selectedLanguage = "hindi"

            }
            otherLanguage.setOnClickListener {
                applyTint(otherLanguage, R.color.primary, R.color.white)
                applyTint(englishLanguage, R.color.color_CECECE, R.color.color_3B3B3B)
                applyTint(hindiLanguage, R.color.color_CECECE, R.color.color_3B3B3B)
                textViewProceed.isEnabled = true
                applyTint(textViewProceed, R.color.primary, R.color.white)

                selectedLanguage = "other"

            }
        }
    }

    private fun applyTint(englishLanguage: TextView, bgColor: Int, textColor: Int) {
        englishLanguage.setTextColor(ContextCompat.getColor(this,textColor))
        englishLanguage.background =
            ContextCompat.getDrawable(this, R.drawable.btn_filled_primary)
        englishLanguage.backgroundTintList = ContextCompat.getColorStateList(this,bgColor)
    }
}