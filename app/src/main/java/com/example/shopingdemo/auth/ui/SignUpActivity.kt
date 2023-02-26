package com.example.shopingdemo.auth.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.res.ResourcesCompat
import com.example.shopingdemo.R
import com.example.shopingdemo.dashboard.ui.DashboardActivity
import com.example.shopingdemo.databinding.ActivitySignUpBinding
import com.example.shopingdemo.server.retrofit.Resource
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.ArrayList

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignInViewModel by viewModel()
    var skilSetList = ArrayList(
        listOf(
            "Mechanic",
            "Labor",
            "Other",
            "Not Available",
            "Select skills"
        )
    )
    val localityList = ArrayList(
        listOf(
            "Marathali",
            "HSR Sector-7",
            "HSR Sector-6",
            "HSR Sector-5",
            "HSR Sector-5",
            "Select locality"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setUpObserver()
    }

    private fun setUpUi() {
        binding.apply {
           textViewSignIn.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            }

            textViewSubmit.setOnClickListener {
                if (validDetails()) {
                    var  params = JSONObject()
                    params.put("name",nameLayout.editText.text.toString())
                    params.put("phone_no",mobLayout.editText.text.toString())
                    var otherData = JSONObject()
                    otherData.put("experience", experienceLayout.editText.text.toString())
                    otherData.put("age", ageLayout.editText.text.toString())
                    otherData.put("profile_type",  "Labour")

                    params.put("data",otherData)

                    viewModel.signUpUser(params)

                    /* viewModel.signUp(
                         name = editTextName.text.toString(),
                         email = editTextEmail.text.toString(),
                         password = editTextPassword.text.toString()
                     )*/
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.mandatory_fields_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            nameLayout.label.text = "Name"
            nameLayout.astrikMarks.visibility = View.VISIBLE
            nameLayout.editText.hint = "Enter name"
            nameLayout.iconIV.visibility = View.GONE

            experienceLayout.label.text = "Experience Years"
            experienceLayout.astrikMarks.visibility = View.GONE
            experienceLayout.editText.hint = "Enter years of experience"

            skillsLayout.label.text = "Skills"
            skillsLayout.astrikMarks.visibility = View.VISIBLE
            localityLayout.label.text = "Locality"

            setUpRelationSpinner(binding.skillsLayout.spinner, skilSetList, "relationship")
            setUpRelationSpinner(binding.localityLayout.spinner, localityList, "gender")

            ageLayout.label.text = "age"
            ageLayout.astrikMarks.visibility = View.GONE
            ageLayout.editText.hint = "Enter age"
            ageLayout.editText.inputType = InputType.TYPE_CLASS_NUMBER
            ageLayout.iconIV.visibility = View.GONE

            mobLayout.label.text = "Mobile number"
            mobLayout.astrikMarks.visibility = View.GONE
            mobLayout.editText.hint = "Enter mobile number"
            mobLayout.editText.inputType = InputType.TYPE_CLASS_PHONE
            mobLayout.iconIV.visibility = View.GONE

            otpLayout.label.text = "Enter OTP"
            otpLayout.astrikMarks.visibility = View.GONE
            otpLayout.editText.hint = "Enter otp"
            otpLayout.editText.inputType = InputType.TYPE_CLASS_NUMBER
            otpLayout.iconIV.visibility = View.GONE

        }

    }

    private fun setUpObserver() {

        viewModel.signUpUser.observe(this) {
            when (it) {
                is Resource.Error -> {
                    binding.progressBar.root.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.e("TAG", "setUpObserver: "+it.message )
                }
                is Resource.Loading -> {
                    binding.progressBar.root.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.root.visibility = View.GONE
                    if (it.data != null)
                        startActivity(Intent(this@SignUpActivity, DashboardActivity::class.java))
                    else
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }


    private fun validDetails(): Boolean {
        return binding.nameLayout.editText.text.toString()
            .isNotEmpty() && binding.mobLayout.editText.text.isNotEmpty()

    }

    private fun setUpRelationSpinner(
        spinner: AppCompatSpinner,
        spinnerList: ArrayList<String>,
        spinnerType: String
    ) {
        val spAdapter = HintAdapter(this, R.layout.row_spinner, spinnerList.toTypedArray())

        spAdapter.setDropDownViewResource(R.layout.row_drop_down_spinner_large)
        spinner.adapter = spAdapter

        spinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?,
                    pos: Int, id: Long
                ) {
                    (view as TextView).setTextColor(ResourcesCompat.getColor(resources,if(spAdapter.count==pos) R.color.color_868686 else R.color.black,null))

                }

                override fun onNothingSelected(arg0: AdapterView<*>?) {}
            }
        spinner.setSelection(spAdapter.count)
    }
}