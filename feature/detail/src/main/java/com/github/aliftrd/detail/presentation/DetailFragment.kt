package com.github.aliftrd.detail.presentation

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.domain.favorite.model.Favorite
import com.github.aliftrd.core.utils.ConstVar
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.detail.R
import com.github.aliftrd.detail.databinding.FragmentDetailBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.ext.android.inject
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.github.aliftrd.core.R as CoreR

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val detailViewModel by inject<DetailViewModel>()
    private val movieId: Int by lazy {
        DetailFragmentArgs.fromBundle(requireArguments()).movieId
    }
    private lateinit var favoriteItem: Favorite

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        //
    }

    override fun initAction() {
        with(binding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun btnFavAction(isFavorite: Boolean) {
        if (isFavorite) {
            detailViewModel.removeFavoriteMovie(favoriteItem)
            Toast.makeText(
                requireContext(),
                getString(R.string.removed_from_favorite),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            detailViewModel.addFavoriteMovie(favoriteItem)
            Toast.makeText(
                requireContext(),
                getString(R.string.added_to_favorite),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun initProcess() {
        detailViewModel.getMovieDetail(movieId)
        detailViewModel.checkIsFavorite(movieId)
    }

    override fun initObserver() {
        detailViewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            with(binding) {
                btnFavorite.apply {
                    setOnClickListener { btnFavAction(isFavorite) }
                    val tintColor = if (isFavorite) CoreR.color.red else CoreR.color.white
                    setColorFilter(
                        ContextCompat.getColor(requireContext(), tintColor)
                    )
                }
            }
        }

        detailViewModel.detailMovie.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RemoteResponse.Loading -> FancyToast.makeText(requireContext(),getString(com.github.aliftrd.core.R.string.loading), FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
                is RemoteResponse.Success -> {
                    val movie = state.data

                    favoriteItem = Favorite(
                        id = movie.id,
                        movieId = movie.id,
                        title = movie.title,
                        posterPath = movie.posterPath,
                        releaseDate = movie.releaseDate,
                        voteAverage = movie.voteAverage,
                        genre = movie.genres[0].name,
                        runtime = movie.runtime
                    )

                    binding.apply {
                        movieTitle.text = movie.title
                        bgMoviePoster.load(ConstVar.BASE_IMAGE_URL + movie.posterPath)
                        moviePoster.load(ConstVar.BASE_IMAGE_URL + movie.posterPath)
                        movieGenre.text = movie.genres[0].name
                        movieRating.text = movie.voteAverage.toString()

                        movie.voteAverage.toBigDecimal().setScale(1, RoundingMode.UP).toString()
                            .also { movieRating.text = it }

                        "${movie.runtime} Minutes".also {
                            movieDuration.text = it
                        }

                        movieStoryLine.text = movie.overview
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            movieStoryLine.justificationMode =
                                LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            val date = LocalDate.parse(movie.releaseDate)
                            val formatter = DateTimeFormatter.ofPattern("yyyy")
                            movieReleaseYear.text = date.format(formatter)
                        } else {
                            movieReleaseYear.text = movie.releaseDate
                        }

                        val castCrewAdapter = CastCrewAdapter()
                        castCrewAdapter.submitList(movie.castCrew)
                        binding.rvCastCrew.apply {
                            adapter = castCrewAdapter
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        }
                    }
                }
                is RemoteResponse.Error -> FancyToast.makeText(requireContext(), state.errorMessage, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                else -> {
                    FancyToast.makeText(requireContext(),getString(com.github.aliftrd.core.R.string.unknown_error), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                }
            }
        }
    }
}