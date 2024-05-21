package com.github.aliftrd.moovai.presentation.detail

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.github.aliftrd.core.domain.movie.model.Movie
import com.github.aliftrd.core.utils.ConstVar
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.moovai.databinding.FragmentDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val movie: Movie by lazy {
            DetailFragmentArgs.fromBundle(requireArguments()).movie
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        binding.apply {
            movieTitle.text = movie.title
            bgMoviePoster.load(ConstVar.BASE_IMAGE_URL + movie.posterPath)
            moviePoster.load(ConstVar.BASE_IMAGE_URL + movie.posterPath)
            movieGenre.text = movie.genre[0].name

            movieDuration.text = "119 Minutes"
            movieStoryLine.text = movie.overview
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                movieStoryLine.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val date = LocalDate.parse(movie.releaseDate)
                val formatter = DateTimeFormatter.ofPattern("yyyy")
                movieReleaseYear.text = date.format(formatter)
            } else {
                movieReleaseYear.text = movie.releaseDate
            }
        }
    }

    override fun initAction() {
        //
    }

    override fun initProcess() {
        //
    }

    override fun initObserver() {
        //
    }
}