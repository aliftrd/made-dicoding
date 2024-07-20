package com.github.aliftrd.search.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.aliftrd.core.databinding.MovieItemHorizontalBinding
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.utils.ConstVar
import com.github.aliftrd.core.utils.ext.gone
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SearchAdapter : ListAdapter<Movie, SearchAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(private val binding: MovieItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                movieDuration.gone()
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

                if (item.genre.isNotEmpty()) {
                    movieGenre.text = item.genre[0].name
                } else {
                    movieGenre.gone()
                }

                root.setOnClickListener {
                    val navigation = SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.id)
                    root.findNavController().navigate(navigation)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: MovieItemHorizontalBinding =
            MovieItemHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        val itemView = holder.itemView
        val isLastItem = position == itemCount - 1
        val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = if (!isLastItem) 18 else 0
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}