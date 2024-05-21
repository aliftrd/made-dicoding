package com.github.aliftrd.moovai.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.utils.ConstVar
import com.github.aliftrd.moovai.databinding.MovieItemVerticalBinding
import java.math.RoundingMode

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {
    class ListViewHolder(private val binding: MovieItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                movieTitle.text = item.title
                moviePoster.load(ConstVar.BASE_IMAGE_URL + item.posterPath)
                item.voteAverage.toBigDecimal().setScale(1, RoundingMode.UP).toString()
                    .also { movieRating.text = it }
                movieGenre.text = item.genre[0].name

                root.setOnClickListener {
                    val navigation = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
                    root.findNavController().navigate(navigation)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: MovieItemVerticalBinding =
            MovieItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)

        val itemView = holder.itemView
        val isLastItem = position == itemCount - 1
        val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
        params.marginEnd = if (!isLastItem) 18 else 0
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