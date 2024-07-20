package com.github.aliftrd.home.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aliftrd.core.R
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.home.databinding.FragmentHomeBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        //
    }

    override fun initAction() {
        binding.favoriteButton.setOnClickListener {
            try {
                val intent = Intent(requireActivity(), Class.forName("com.github.aliftrd.favorite.presentation.MainActivity"))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(requireActivity(), "Feature not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initProcess() {
        //
    }

    override fun initObserver() {
        homeViewModel.apply {
            nowPlayingMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is RemoteResponse.Loading -> FancyToast.makeText(requireContext(),getString(R.string.loading), FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
                    is RemoteResponse.Success -> {
                        val nowPlayingAdapter = CarouselMovieAdapter()
                        nowPlayingAdapter.submitList(it.data)
                        binding.rvNowPlaying.apply {
                            adapter = nowPlayingAdapter
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        }
                    }
                    is RemoteResponse.Error -> FancyToast.makeText(requireContext(), it.errorMessage, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                    else -> {
                        FancyToast.makeText(requireContext(),getString(R.string.unknown_error), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                    }
                }
            }

            popularMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is RemoteResponse.Loading ->Log.d("HomeFragmentLoading", "initObserver: Loading")
                    is RemoteResponse.Success -> {
                        val popularAdapter = MovieAdapter()
                        popularAdapter.submitList(it.data)
                        binding.rvMostPopular.apply {
                            adapter = popularAdapter
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        }
                    }
                    is RemoteResponse.Error -> Log.d("HomeFragmentError", "initObserver: ${it.errorMessage}")
                    is RemoteResponse.Empty -> Log.d("HomeFragmentEmpty", "initObserver: Empty")
                }
            }
        }
    }
}