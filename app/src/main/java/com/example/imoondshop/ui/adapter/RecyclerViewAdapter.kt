package com.example.imoondshop.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imoondshop.R
import com.imoond.data.models.productModels.ProductModelItem

class RecyclerViewAdapter :
    PagingDataAdapter<ProductModelItem, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = itemView.findViewById<ImageView>(R.id.iv_ProductItem)
        private val title = itemView.findViewById<TextView>(R.id.tvNameProductItem)
        private val cost = itemView.findViewById<TextView>(R.id.tvCostProductItem)
        private val description = itemView.findViewById<TextView>(R.id.tv_descriptionProductItem)
        fun bind(product: ProductModelItem?) {
            Log.d("TAG", "bind: ${product?.id}")
            if (product != null) {
                Glide.with(itemView)
                    .load(product.images[0].src)
                    .centerCrop()
                    .placeholder(R.drawable.product_item)
                    .into(image)

                title.text = product.name
                cost.text = product.price
                description.text = product.categories[0].name
            } else {
                Log.d("TAG", "bind: Product is empty")
            }


        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ProductModelItem>() {
        override fun areItemsTheSame(
            oldItem: ProductModelItem,
            newItem: ProductModelItem
        ): Boolean {
            return oldItem.id == newItem.id
            // return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ProductModelItem,
            newItem: ProductModelItem
        ): Boolean {
            return oldItem == newItem
            // return oldItem.name == newItem.name && oldItem.categories[0].name == newItem.categories[0].name
        }

    }
}