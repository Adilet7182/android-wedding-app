package com.example.weddingguesttracker.data.models

    data class Guest(
        val name: String,
        val age: Int,
        val gender: Gender,
        val side: Side,
        val tableId: Int
    )

    enum class Gender{MALE, FEMALE}
    enum class Side{GROOM, BRIDE}
