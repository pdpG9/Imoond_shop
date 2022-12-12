package com.example.imoondshop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imoondshop.R

class ImagePagerAdapter(val list: List<String>) :
    RecyclerView.Adapter<ImagePagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       private val imageView: ImageView = itemView.findViewById<ImageView>(R.id.iv_imagepager)
        fun bind(position: Int) {
            Glide.with(itemView.context).load(list.get(position)).into(imageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_pager, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}