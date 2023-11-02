package com.example.dodotesttask.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dodotesttask.R
import com.example.dodotesttask.model.MenuCategory

class MenuCategoryAdapter : ListAdapter<MenuCategory, MenuCategoryAdapter.MenuCategoryViewHolder>(MenuCategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_category_item, parent, false)
        return MenuCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuCategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    class MenuCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.findViewById(R.id.categoryName)

        fun bind(category: MenuCategory) {
            categoryName.text = category.name
        }
    }

    class MenuCategoryDiffCallback : DiffUtil.ItemCallback<MenuCategory>() {
        override fun areItemsTheSame(oldItem: MenuCategory, newItem: MenuCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MenuCategory, newItem: MenuCategory): Boolean {
            return oldItem == newItem
        }
    }
}
