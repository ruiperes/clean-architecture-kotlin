package com.cardinal.cleanarchitecture.domain.interactors

import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.cardinal.cleanarchitecture.domain.gateway.FoodGateway
import com.cardinal.core.domain.exceptions.Failure
import com.cardinal.core.framework.funcional.Either
import com.cardinal.core.domain.interactor.UseCase

class GetTopUseCase(private val foodGateway: FoodGateway) : UseCase<List<Recipe>, GetTopUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<Recipe>> {
        return try {
            Either.Right(foodGateway.getTopFood(params.page))
        } catch (e: Exception) {
            Either.Left(GetTopFailure(e))
        }
    }

    data class Params(val page: Int = 1)
    class GetTopFailure(error: Exception) : Failure.FeatureFailure(error)
}