package com.example.imoondshop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imoondshop.R
import com.imoond.domain.model.CategoryModel

class CategoryFragmentAdapter(val list: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryFragmentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = itemView.findViewById<ImageView>(R.id.iv_category_fragment_item)
        val name = itemView.findViewById<TextView>(R.id.tv_name_category_fragment_item)
        fun bind(position: Int) {
            val data = list.get(position)
            name.text = data.name
            Glide.with(itemView)
                .load(data.imageUrl)
                .centerCrop()
                .into(image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_large, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)
}