package com.example.shonenapp.domain.model

import com.example.models.ShonenCharacterResponse
import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val characters: List<ShonenCharacterResponse> = emptyList(),
    val lastUpdated:Long?=null
)