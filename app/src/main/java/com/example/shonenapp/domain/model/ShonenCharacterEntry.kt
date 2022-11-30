package com.example.shonenapp.domain.model

data class ShonenCharacterEntry(
    val id: Long,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val strength: Int,
    val month: String,
    val day: String,
    val related: List<String>,
    val elements: List<String>,
    val skillSet: List<String>
)
