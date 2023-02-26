package com.example.shopingdemo.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopingdemo.R
import com.example.shopingdemo.dashboard.model.ProductsModel
import com.example.shopingdemo.databinding.ProductItemRowBinding
import com.example.shopingdemo.databinding.RewardRowBinding


class ProductsAdapter(
    val context: Context,
    val onClick: (ProductsModel) -> Unit,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var productList = arrayListOf<ProductsModel>()

    lateinit var productListView: RecyclerView


    inner class ViewHolder(val binding: ProductItemRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolder1(val binding1: RewardRowBinding) :
        RecyclerView.ViewHolder(binding1.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolder(
                ProductItemRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> ViewHolder1(
                RewardRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (getItemViewType(position)) {
            0 -> {
                with(holder as ViewHolder) {
                    with(productList[position]) {

                        binding.productName.text = name
                        binding.productDescription.text = productDesc
                        binding.productPrice.text = "INR $price"
                        Glide
                            .with(context)
                            .load(productUrl)
                            .centerCrop()
                            .placeholder(androidx.core.R.drawable.notification_template_icon_bg)
                            .into(binding.productIcon)

                        binding.root.setOnClickListener {
                            onClick(with@ this)
                        }
                    }
                }
            }
            1 -> {
                with(holder as ViewHolder1) {
                    binding1.labelText.text = context.getString(R.string.rewards_text)
                    binding1.root.setOnClickListener {
                        Toast.makeText(
                            context,
                            context.getString(R.string.thanks_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }

    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 1) 1 else 0
    }


    fun setData(productList: ArrayList<ProductsModel>) {
        this.productList.addAll(productList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productList.size
}