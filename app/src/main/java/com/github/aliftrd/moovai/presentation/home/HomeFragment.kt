package com.github.aliftrd.moovai.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.moovai.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel by inject<HomeViewModel>()

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
        //
    }

    override fun initProcess() {
        homeViewModel.getAllNowPlaying()
        homeViewModel.getAllPopular()
    }

    override fun initObserver() {
        homeViewModel.apply {
            nowPlayingMovies.observe(viewLifecycleOwner) {
                when (it) {
                    is RemoteResponse.Loading -> Log.d("HomeFragmentLoading", "initObserver: Loading")
                    is RemoteResponse.Success -> {
                        val nowPlayingAdapter = CarouselMovieAdapter()
                        nowPlayingAdapter.submitList(it.data)
                        binding.rvNowPlaying.apply {
                            adapter = nowPlayingAdapter
                            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        }
                    }
                    is RemoteResponse.Error -> Log.d("HomeFragmentError", "initObserver: ${it.errorMessage}")
                    is RemoteResponse.Empty -> Log.d("HomeFragmentEmpty", "initObserver: Empty")
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