package com.example.shonenapp.domain.use_case

import com.example.shonenapp.domain.use_case.get_all_character_use_case.GetAllCharacterUseCase
import com.example.shonenapp.domain.use_case.get_onboarding_use_case.GetOnboardingUseCase
import com.example.shonenapp.domain.use_case.get_selected_character_use_case.GetSelectedCharacterUseCase
import com.example.shonenapp.domain.use_case.save_onboarding_use_case.SaveOnboardingUseCase
import com.example.shonenapp.domain.use_case.search_character_use_case.SearchCharacterUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val getOnboardingUseCase: GetOnboardingUseCase,
    val getAllCharacterUseCase: GetAllCharacterUseCase,
    val searchCharacterUseCase:SearchCharacterUseCase,
    val getSelectedCharacterUseCase: GetSelectedCharacterUseCase
)
