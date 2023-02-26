package com.example.shopingdemo.dashboard.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shopingdemo.dashboard.model.ProductsModel
import com.example.shopingdemo.databinding.FragmentProductDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ProductDetailsFragment : BottomSheetDialogFragment() {
    private var product: ProductsModel? = null
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding
    private val ARG_PARAM1 = "product"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi() {
        binding?.apply {
            productBrand.text = product?.brand
            productName.text = product?.name
            productDescription.text = product?.productDesc
            productPrice.text = product?.price
            productPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            offerProductPrice.text = product?.offerPrice
            Glide
                .with(requireActivity())
                .load(product?.productUrl)
                .centerCrop()
                .placeholder(androidx.core.R.drawable.notification_template_icon_bg)
                .into(productIcon)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(product: ProductsModel) =
            ProductDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, product)
                }
                return@apply
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}