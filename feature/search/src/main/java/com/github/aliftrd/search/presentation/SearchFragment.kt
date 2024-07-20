package com.github.aliftrd.search.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aliftrd.core.data.lib.RemoteResponse
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.search.R
import com.github.aliftrd.search.databinding.FragmentSearchBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        //
    }

    override fun initAction() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.setText(searchView.text)
                searchView.hide()
                searchViewModel.searchMovie(searchBar.text.toString())
                false
            }
        }
    }

    override fun initProcess() {
        //
    }

    override fun initObserver() {
        searchViewModel.searchedMovie.observe(viewLifecycleOwner) {
            when (it) {
                is RemoteResponse.Loading -> FancyToast.makeText(requireContext(),getString(com.github.aliftrd.core.R.string.loading), FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
                is RemoteResponse.Success -> {
                    val searchAdapter = SearchAdapter()
                    searchAdapter.submitList(it.data)

                    binding.rvSearchMovie.apply {
                        adapter = searchAdapter
                        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    }
                }
                is RemoteResponse.Error -> FancyToast.makeText(requireContext(), it.errorMessage, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                else -> {
                    FancyToast.makeText(requireContext(),getString(R.string.unknown_error), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show()
                }
            }
        }
    }
}