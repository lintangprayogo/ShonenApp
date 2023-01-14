package com.example.shonenapp.data.remote

import com.example.models.ShonenCharacterResponse
import com.example.shonenapp.domain.model.ApiResponse

class FakeShonenApi : ShonenApiService {

    private val characters = listOf(
        ShonenCharacterResponse(
            id = 1,
            name = "Rengoku",
            image = "/images/rengoku.jpg",
            about = "Kyojuro Rengoku (煉れん獄ごく 杏きょう寿じゅ郎ろう Rengoku Kyōjurō?) was a major supporting character of Demon Slayer: Kimetsu no Yaiba and the deuteragonist of the Mugen Train Arc. He was a Demon Slayer of the Demon Slayer Corps and the late Flame Hashira (炎えん柱ばしら En Bashira?).",
            rating = 4.8,
            strength = 75,
            month = "Jan",
            day = "1st",
            related = listOf(
                "Shinjuro Rengoku",
                "Ruka Rengoku",
                "Senjuro Rengoku"
            ),
            skillSet = listOf(
                "First Form: Unknowing Fire",
                "Second Form: Rising Scorching Sun",
                "Third Form: Blazing Universe",
                "Fourth Form: Blooming Flame Undulation",
                "Fifth Form: Flame Tiger",
                "Ninth Form: Rengoku",
            ),
            elements = listOf(
                "Fire"
            )
        ),
        ShonenCharacterResponse(
            id = 2,
            name = "Naruto",
            image = "/images/naruto.jpg",
            about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
            rating = 5.0,
            strength = 98,
            month = "Oct",
            day = "10th",
            related = listOf(
                "Minato",
                "Kushina",
                "Boruto",
                "Himawari",
                "Hinata"
            ),
            skillSet = listOf(
                "Rasengan",
                "Rasen-Shuriken",
                "Shadow Clone",
                "Senin Mode"
            ),
            elements = listOf(
                "Wind",
                "Earth",
                "Lava",
                "Fire"
            )
        ),
        ShonenCharacterResponse(
            id = 3,
            name = "Sakura",
            image = "/images/sakura.jpg",
            about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
            rating = 4.5,
            strength = 92,
            month = "Mar",
            day = "28th",
            related = listOf(
                "Kizashi",
                "Mebuki",
                "Sarada",
                "Sasuke"
            ),
            skillSet = listOf(
                "Chakra Control",
                "Medical Ninjutsu",
                "Strength",
                "Intelligence"
            ),
            elements = listOf(
                "Earth",
                "Water",
                "Fire"
            )
        )
    )

    override suspend fun getAllShonenCharacters(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "success",
        )
    }

    override suspend fun searchShonenCharacters(name: String): ApiResponse {
        return ApiResponse(
            success = true,
            message = "success",
            characters = findCharacters(name)
        )
    }

    private fun findCharacters(name: String): List<ShonenCharacterResponse> {
        return if (name.isNotEmpty()) {
            return characters.filter { character ->
                character.name.contains(name, false)
            }
        } else {
            emptyList()
        }

    }
}