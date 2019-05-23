package com.cardinal.cleanarchitecture.domain.gateway

import com.cardinal.cleanarchitecture.domain.entities.Recipe

interface FoodGateway {

    suspend fun getTopFood(page: Int = 0) : List<Recipe>

    suspend fun getDetail(id: Long)
}