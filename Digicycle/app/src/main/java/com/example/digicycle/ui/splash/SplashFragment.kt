package com.example.digicycle.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.digicycle.R
import com.example.digicycle.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.decideNavigation()
        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { destination ->
                navigateTo(destination)
            }
        }
    }

    private fun navigateTo(destination: SplashViewModel.NavigationDestination) {
        val action = when (destination) {
            SplashViewModel.NavigationDestination.DASHBOARD -> R.id.action_splashFragment_to_loginFragment
            SplashViewModel.NavigationDestination.ONBOARDING -> R.id.action_splashFragment_to_onboardingFragment
            SplashViewModel.NavigationDestination.LOGIN -> R.id.action_splashFragment_to_loginFragment
        }

        findNavController().navigate(action, null,
            androidx.navigation.NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}