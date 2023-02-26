package com.example.shopingdemo.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.shopingdemo.dashboard.model.ProfileModel
import com.example.shopingdemo.dashboard.ui.viewmodels.DashboardViewModel
import com.example.shopingdemo.databinding.FragmentProfileBinding
import com.example.shopingdemo.server.retrofit.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding
    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getProfile()
    }

    private fun setUpObserver() {
        viewModel.profile.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding?.progressBar?.root?.visibility = GONE
                    binding?.scrollView?.visibility = GONE
                }
                is Resource.Loading -> {
                    binding?.progressBar?.root?.visibility = VISIBLE
                    binding?.scrollView?.visibility = GONE
                }
                is Resource.Success -> {
                    binding?.progressBar?.root?.visibility = GONE
                    binding?.scrollView?.visibility = VISIBLE
                    if (it?.data != null) {
                        setUpUi(it.data)
                    }

                }
            }
        }
    }

    private fun setUpUi(data: ProfileModel) {

        binding?.apply {

            Glide
                .with(requireActivity())
                .load(androidx.core.R.drawable.notification_template_icon_bg)
                .placeholder(androidx.core.R.drawable.notification_template_icon_bg)
                .circleCrop()
                .into(userProfile)
            data.run {
                userName.text = "$firstname $lastName"
                userNameValue.text = username
                dobValue.text = dob
                addressValue.text = address
                balanceValue.text = "INR $walletBalance"
                earnedPointValue.text = pointsEarned
            }

        }
    }

}