package com.cardinal.cleanarchitecture.domain.entities

data class Recipe(
    val id: String,
    val title: String,
    val image: String,
    val rank: Int,
    val ingredients: List<String>?,
    val publisher: Publisher)