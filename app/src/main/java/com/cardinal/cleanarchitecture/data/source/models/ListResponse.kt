package com.cardinal.cleanarchitecture.data.source.models

import com.squareup.moshi.Json

data class ListResponse (
    @field:Json(name = "count") val count : Int,
    @field:Json(name = "recipes") val recipes : List<RecipeListItemResponse>
)