package com.example.shopingdemo.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopingdemo.dashboard.adapter.ProductsAdapter
import com.example.shopingdemo.dashboard.ui.viewmodels.DashboardViewModel
import com.example.shopingdemo.databinding.FragmentDashboardBinding
import com.example.shopingdemo.server.retrofit.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardFragment : Fragment() {


    private lateinit var productAdapter: ProductsAdapter
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding
    private val viewModel: DashboardViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProduct()
        setupUi()
        setupObserver()

    }

    private fun setupUi() {
        binding?.apply {

            productAdapter = ProductsAdapter(requireContext()) {
                val fragmentDetails = ProductDetailsFragment.newInstance(it)
                fragmentDetails.show(
                    childFragmentManager,
                    fragmentDetails.tag
                )
            }
            val linearLayoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
            productListView.adapter = productAdapter
            productListView.layoutManager = linearLayoutManager

        }
    }

    private fun setupObserver() {
        viewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    _binding?.progressBar?.root?.visibility = GONE
                }
                is Resource.Loading -> {
                    _binding?.progressBar?.root?.visibility = VISIBLE
                }
                is Resource.Success -> {
                    _binding?.progressBar?.root?.visibility = GONE
                    if (it.data?.data?.products?.isNotEmpty() == true) {
                        it.data?.data?.products?.let { list ->
                            productAdapter.setData(list)
                        }

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}