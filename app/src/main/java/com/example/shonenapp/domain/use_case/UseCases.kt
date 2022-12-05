package com.example.shonenapp.domain.use_case

import com.example.shonenapp.domain.use_case.get_onboarding_use_case.GetOnboardingUseCase
import com.example.shonenapp.domain.use_case.save_onboarding_use_case.SaveOnboardingUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val getOnboardingUseCase: GetOnboardingUseCase,
)
