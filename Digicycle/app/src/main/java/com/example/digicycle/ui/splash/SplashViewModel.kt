package com.example.digicycle.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.digicycle.util.Event
import com.example.digicycle.util.SharedPreferencesHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    enum class NavigationDestination {
        DASHBOARD,
        ONBOARDING,
        LOGIN
    }

    private val _navigationEvent = MutableLiveData<Event<NavigationDestination>>()
    val navigationEvent: LiveData<Event<NavigationDestination>> = _navigationEvent

    private val prefsHelper = SharedPreferencesHelper(application)

    companion object {
        const val SPLASH_DELAY = 2000L
    }

    fun decideNavigation() {
        viewModelScope.launch {

            delay(SPLASH_DELAY)

            val token = prefsHelper.getToken()
            val isOnboardingComplete = prefsHelper.isOnboardingComplete()

            val isLoggedIn = !token.isNullOrEmpty()

            if (isLoggedIn) {
                _navigationEvent.postValue(Event(NavigationDestination.DASHBOARD))
            } else {
                if (isOnboardingComplete) {
                    _navigationEvent.postValue(Event(NavigationDestination.LOGIN))
                } else {
                    _navigationEvent.postValue(Event(NavigationDestination.ONBOARDING))
                }
            }
        }
    }
}