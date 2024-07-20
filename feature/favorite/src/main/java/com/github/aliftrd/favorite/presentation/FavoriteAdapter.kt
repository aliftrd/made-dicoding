package com.github.aliftrd.favorite.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.aliftrd.core.databinding.MovieItemHorizontalBinding
import com.github.aliftrd.core.domain.favorite.model.Favorite
import com.github.aliftrd.core.utils.ConstVar
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FavoriteAdapter : ListAdapter<Favorite, FavoriteAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(private val binding: MovieItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Favorite) {
            with(binding) {
                movieTitle.text = item.title
                moviePoster.load(ConstVar.BASE_IMAGE_URL + item.posterPath)
                item.voteAverage.toBigDecimal().setScale(1, RoundingMode.UP).toString()
                    .also { movieRating.text = it }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val date = LocalDate.parse(item.releaseDate)
                    val formatter = DateTimeFormatter.ofPattern("yyyy")
                    movieReleaseDate.text = date.format(formatter)
                } else {
                    movieReleaseDate.text = item.releaseDate
                }

                "${item.runtime} Minutes".also { movieDuration.text = it }

                movieGenre.text = item.genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: MovieItemHorizontalBinding =
            MovieItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        val itemView = holder.itemView
        val isLastItem = position == itemCount - 1
        val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = if (!isLastItem) 18 else 0
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }
    }
}