package com.example.movee.feature.caster.review

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movee.data.repository.review.Review
import com.example.movee.databinding.ItemReviewBinding

class ReviewAdapter : PagingDataAdapter<Review, ReviewAdapter.ViewHolder>(ReviewMovieDiffUtil) {
    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { review -> holder.bind(review) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ReviewAdapter.ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) = with(binding) {
            textReview.text = review.review
            imageAuthor.load(review.author.getAvatarUrl())
            textUsername.text = review.author.username
            rating.rating = (review.author.rating * 5 / 10).toFloat()
            textReview.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(review.review, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(review.review)
            }
        }
    }
}


object ReviewMovieDiffUtil : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

}