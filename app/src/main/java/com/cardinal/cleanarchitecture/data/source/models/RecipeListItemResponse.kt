package com.cardinal.cleanarchitecture.data.source.models

import com.cardinal.cleanarchitecture.domain.entities.Publisher
import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.squareup.moshi.Json

data class RecipeListItemResponse (
    @field:Json(name = "publisher") val publisher : String,
    @field:Json(name = "f2f_url") val f2f_url : String,
    @field:Json(name = "title") val title : String,
    @field:Json(name = "source_url") val source_url : String,
    @field:Json(name = "recipe_id") val recipe_id : String,
    @field:Json(name = "image_url") val image_url : String,
    @field:Json(name = "social_rank") val social_rank : Int,
    @field:Json(name = "publisher_url") val publisher_url : String
) {
    fun map() : Recipe {
        return Recipe(recipe_id, title, image_url, social_rank, null, Publisher(0, publisher, publisher_url))
    }
}