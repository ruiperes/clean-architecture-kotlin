package com.cardinal.cleanarchitecture.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cardinal.cleanarchitecture.R
import com.cardinal.cleanarchitecture.view.common.autoNotify
import com.cardinal.cleanarchitecture.view.common.loadFromUrl
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlin.properties.Delegates

internal class RecipeAdapter(private val action: (String) -> Unit = {}) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    var items: List<RecipeUiItem> by Delegates.observable(
        emptyList()
    ) { _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o.id == n.id }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]
        holder.id = recipe.id
        holder.name.text = recipe.title
        holder.photo.loadFromUrl(recipe.imageUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    fun getItemAtPosition(childAdapterPosition: Int) = items[childAdapterPosition]

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        init {
            v.setOnClickListener{ action(id) }
        }

        val name: TextView = v.tvTitle
        val photo: ImageView = v.ivPhoto
        lateinit var id: String
    }
}