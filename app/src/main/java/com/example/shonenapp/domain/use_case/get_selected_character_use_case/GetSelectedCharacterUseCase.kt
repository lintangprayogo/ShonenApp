package com.example.shonenapp.domain.use_case.get_selected_character_use_case

import com.example.shonenapp.data.repository.Repository

class GetSelectedCharacterUseCase(private val repository: Repository) {

    suspend operator fun invoke(id: Long) = repository.getSelectedCharacter(id)
}