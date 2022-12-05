package com.example.shonenapp.domain.use_case.get_onboarding_use_case

import com.example.shonenapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetOnboardingUseCase(private val repository: Repository) {

   operator fun invoke(): Flow<Boolean> {
      return  repository.getOnBoardingState()
    }
}