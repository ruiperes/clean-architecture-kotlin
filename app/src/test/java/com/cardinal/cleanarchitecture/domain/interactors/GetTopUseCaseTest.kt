package com.cardinal.cleanarchitecture.domain.interactors

import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.cardinal.cleanarchitecture.domain.gateway.FoodGateway
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.MainScope
import org.junit.Test

class GetTopUseCaseTest {

    private val gateway = mockk<FoodGateway>()

    @Test
    fun `Either Right should return correct type and value`(){
        // Arrange
        val dummy = mockk<Recipe>()
        coEvery { gateway.getTopFood(any()) } returns listOf(mockk())

        // Act
        val useCase = GetTopUseCase(gateway)
        useCase(MainScope(), GetTopUseCase.Params(1))


        // Assert
    }
}