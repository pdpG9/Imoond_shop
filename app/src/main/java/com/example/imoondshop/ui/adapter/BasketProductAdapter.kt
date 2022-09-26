package com.example.imoondshop.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imoondshop.R
import com.imoond.domain.model.ProductEntity

class BasketProductAdapter(val list: List<ProductEntity>,private val listener: ProductClickListener) :
    RecyclerView.Adapter<BasketProductAdapter.ViewHolder>() {
    private var amountOfAllProductPrice = 0

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image = itemView.findViewById<ImageView>(R.id.iv_itemCard)
        private val title = itemView.findViewById<TextView>(R.id.tv_nameProductCard)
        private val description = itemView.findViewById<TextView>(R.id.tv_descriptionProductCard)
        private val cost = itemView.findViewById<TextView>(R.id.tv_costProductCard)
        private val count = itemView.findViewById<TextView>(R.id.tv_countProductCard)
        private val btAdd = itemView.findViewById<CardView>(R.id.btAddCount)
        private val btRemove = itemView.findViewById<CardView>(R.id.btRemoveCount)
        private val  btSelect = itemView.findViewById<ImageButton>(R.id.rbt_selectedItem)
        fun bind(position: Int) {
            val product = list[position]

            btAdd.setOnClickListener {
                 product.cardCount++
                count.text = product.cardCount.toString()
                if (product.isSelected){
                    amountOfAllProductPrice += product.price.toInt()
                   updateAllPrice()
                }
            }
            btRemove.setOnClickListener {
                if (product.cardCount>1){
                product.cardCount--
                count.text = product.cardCount.toString()
                    if (product.isSelected){
                        amountOfAllProductPrice -= product.price.toInt()
                        updateAllPrice()
                    }
                }
            }
            btSelect.setOnClickListener {
                product.isSelected = !product.isSelected
                if (product.isSelected){
                    btSelect.setImageResource(R.drawable.ic_check)
                    amountOfAllProductPrice += product.price.toInt()*product.cardCount
                }else{
                    amountOfAllProductPrice -= product.price.toInt()*product.cardCount
                    btSelect.setImageResource(R.drawable.ic_uncheck)
                }
                updateAllPrice()

            }
            Glide.with(itemView)
                .load(product.images[0])
                .centerCrop()
                .placeholder(R.drawable.product_item)
                .into(image)
            title.text = product.name
            cost.text = product.price
            description.text = product.category
        }
    }
    private fun updateAllPrice(){
        listener.onClick(amountOfAllProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}