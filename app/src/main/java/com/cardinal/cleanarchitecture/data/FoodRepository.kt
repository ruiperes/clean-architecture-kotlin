package com.cardinal.cleanarchitecture.data

import com.cardinal.cleanarchitecture.data.source.NetworkSource
import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.cardinal.cleanarchitecture.domain.gateway.FoodGateway

class FoodRepository(private val networkSource: NetworkSource) : FoodGateway {

    override suspend fun getTopFood(page: Int): List<Recipe> {
        return networkSource.getTop(page)
    }

    override suspend fun getDetail(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}