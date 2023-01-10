package com.example.movee.feature.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movee.data.repository.genre.Genre
import com.example.movee.databinding.ItemGenreBinding

class GenreAdapter(
    private val list: List<Genre>, private val onGenreSelected: (id: Int, name: String) -> Unit
) : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], onGenreSelected)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre, onGenreSelected: (id: Int, name: String) -> Unit) {
            binding.root.apply {
                text = genre.name
                setOnClickListener { onGenreSelected.invoke(genre.id, genre.name) }
            }
        }
    }
}