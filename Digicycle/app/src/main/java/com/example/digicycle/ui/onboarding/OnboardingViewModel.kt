package com.example.digicycle.ui.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.digicycle.util.SharedPreferencesHelper

class OnboardingViewModel(application: Application) : AndroidViewModel(application) {

    private val prefsHelper = SharedPreferencesHelper(application)
    fun completeOnboarding() {
        prefsHelper.setOnboardingComplete(true)
    }
}