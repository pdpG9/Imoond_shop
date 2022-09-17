package com.example.imoondshop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imoondshop.R
import com.imoond.domain.model.CategoryModel

class CategoryAdapter(val list: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val colorList =
        listOf(R.color.cerulean, R.color.yellow, R.color.blue, R.color.ink)
    private var indexColor = 0
    private val listSize = colorList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: AppCompatTextView =
            itemView.findViewById<AppCompatTextView>(R.id.tv_title_category_item)
        private val layout = itemView.findViewById<LinearLayout>(R.id.item_category_layout)
        fun bind(position: Int) {
            val data = list.get(position)
            name.text = data.name
            indexColor = position % listSize

            layout.setBackgroundResource(colorList[indexColor])

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}