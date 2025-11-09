package com.example.digicycle.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.digicycle.ui.onboarding.OnboardingPageFragment

class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingPageFragment.newInstance(
                "Selamat Datang di DigiCycle!",
                "Pilah Sampah, Lindungi Bumi, Kumpulkan Poin!"
            )
            1 -> OnboardingPageFragment.newInstance(
                "Pilah & Tukar Sampahmu",
                "Kategorikan sampahmu. Tukar dengan DigiPoin!"
            )
            2 -> OnboardingPageFragment.newInstance(
                "Wujudkan Dampak Nyata",
                "Setiap aksi pilahmu bantu lestarikan lingkungan. Dapatkan rewardmu disini!"
            )
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}