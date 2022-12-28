package com.example.shonenapp.domain.use_case

import com.example.shonenapp.data.paging_source.SearchCharacterSource
import com.example.shonenapp.domain.get_all_character_use_case.GetAllCharacterUseCase
import com.example.shonenapp.domain.use_case.get_onboarding_use_case.GetOnboardingUseCase
import com.example.shonenapp.domain.use_case.save_onboarding_use_case.SaveOnboardingUseCase
import com.example.shonenapp.domain.use_case.search_character_use_case.SearchCharacterUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val getOnboardingUseCase: GetOnboardingUseCase,
    val getAllCharacterUseCase: GetAllCharacterUseCase,
    val searchCharacterUseCase:SearchCharacterUseCase
)
