package com.example.movee.feature.discovermovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movee.BuildConfig
import com.example.movee.data.repository.discover.DiscoverMovie
import com.example.movee.databinding.ItemDiscoverMovieBinding

class DiscoverMovieAdapter(
    private val onClicked: (id: Int) -> Unit
) : PagingDataAdapter<DiscoverMovie, DiscoverMovieAdapter.ViewHolder>(DiscoverMovieDiffUtil) {
    override fun onBindViewHolder(holder: DiscoverMovieAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { movie -> holder.bind(movie, onClicked) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DiscoverMovieAdapter.ViewHolder {
        val binding =
            ItemDiscoverMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemDiscoverMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(discoverMovie: DiscoverMovie, onClicked: (id: Int) -> Unit) = with(binding) {
            textTitle.text = discoverMovie.title
            backdrop.load(buildString {
                append(BuildConfig.BASE_URL_IMAGE)
                append(discoverMovie.backdrop)
            })
            root.setOnClickListener { onClicked.invoke(discoverMovie.id) }
        }
    }
}


object DiscoverMovieDiffUtil : DiffUtil.ItemCallback<DiscoverMovie>() {
    override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
        return oldItem == newItem
    }

}