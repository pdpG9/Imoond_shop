package com.example.imoondshop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imoondshop.R
import com.imoond.domain.model.ProductModel

class ProductAdapter(val list: List<ProductModel>, listener: ProductClickListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val listener = listener

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = itemView.findViewById<ImageView>(R.id.iv_ProductItem)
        private val title = itemView.findViewById<TextView>(R.id.tvNameProductItem)
        private val cost = itemView.findViewById<TextView>(R.id.tvCostProductItem)

        //        private val favButton = itemView.findViewById<AppCompatImageButton>(R.id.bt_favourite)
        private val description = itemView.findViewById<TextView>(R.id.tv_descriptionProductItem)
        fun bind(position: Int) {
            val product = list[position]
            itemView.setOnClickListener {
                listener.onClick(product.id)
            }
            Glide.with(itemView)
                .load(product.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.product_item)
                .into(image)
            title.text = product.title
            cost.text = product.price
//            if (product.isFavourite){
//            favButton.setImageResource(R.drawable.ic_favorite_check)
//            }else{
//            favButton.setImageResource(R.drawable.ic_favorite_uncheck)
//            }
            description.text = product.category

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}

interface ProductClickListener {
    fun onClick(position: Int)
}