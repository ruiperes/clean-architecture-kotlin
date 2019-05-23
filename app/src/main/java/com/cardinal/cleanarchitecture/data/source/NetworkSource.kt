package com.cardinal.cleanarchitecture.data.source

import com.cardinal.cleanarchitecture.data.source.models.ListResponse
import com.cardinal.cleanarchitecture.domain.entities.Recipe
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

class NetworkSource(val api: FoodApi) {

    suspend fun getTop(page: Int) : List<Recipe> {
        val response = api.getTop(page).await()
        return response.recipes.map { it.map() }
    }

}

interface FoodApi {

    @GET("search")
    fun getTop(@Query("page") page: Int) : Deferred<ListResponse>
}