package com.github.aliftrd.favorite.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.aliftrd.favorite.databinding.ActivityMainBinding
import com.github.aliftrd.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val loadFeatures by lazy { loadKoinModules(favoriteModule) }
    private fun injectFeatures() = loadFeatures

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        favoriteViewModel.getFavoriteMovie()

        favoriteViewModel.favorite.observe(this) {
            Log.d("Favorite", "onCreate: $it")
            val adapter = FavoriteAdapter()
            adapter.submitList(it)

            binding.apply {
                rvFavoriteMovies.adapter = adapter
            }
        }
    }
}