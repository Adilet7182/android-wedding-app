package com.example.weddingguesttracker.data.models

data class Table (
    val id: Int = 0,
    val name: String,
    val guests: List<Guest> = emptyList(),
    var isExpanded: Boolean = true // тут нужен val, а не var, нужно будет изменить это потом.
)