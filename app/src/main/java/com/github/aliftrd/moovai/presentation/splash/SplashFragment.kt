package com.github.aliftrd.moovai.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.aliftrd.core.utils.ext.BaseFragment
import com.github.aliftrd.moovai.R
import com.github.aliftrd.moovai.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun initIntent() {
        //
    }

    override fun initUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000L)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
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