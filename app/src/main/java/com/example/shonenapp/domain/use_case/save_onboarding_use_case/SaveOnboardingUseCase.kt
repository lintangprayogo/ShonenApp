package com.example.shonenapp.domain.use_case.save_onboarding_use_case

import com.example.shonenapp.data.repository.Repository

class SaveOnboardingUseCase(private val repository: Repository) {

    suspend operator fun invoke(completed:Boolean) {
        repository.saveOnBoardingState(completed)
    }
}