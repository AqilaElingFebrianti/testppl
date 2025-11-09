package com.example.digicycle.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.digicycle.R
import com.example.digicycle.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OnboardingAdapter(this)
        binding.viewPagerOnboarding.adapter = adapter

        TabLayoutMediator(binding.tabLayoutDots, binding.viewPagerOnboarding) { tab, position ->
        }.attach()

        binding.btnSkip.setOnClickListener {
            navigateToLogin()
        }

        binding.btnNext.setOnClickListener {
            if (binding.viewPagerOnboarding.currentItem == adapter.itemCount - 1) {
                navigateToLogin()
            } else {
                binding.viewPagerOnboarding.currentItem += 1
            }
        }

        binding.viewPagerOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.btnNext.text = getString(R.string.mulai)
                } else {
                    binding.btnNext.text = getString(R.string.next)
                }
            }
        })
    }

    private fun navigateToLogin() {
        viewModel.completeOnboarding()
        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment, null,
            androidx.navigation.NavOptions.Builder()
                .setPopUpTo(R.id.onboardingFragment, true)
                .build()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}