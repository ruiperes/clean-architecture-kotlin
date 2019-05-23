package com.cardinal.cleanarchitecture.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.cardinal.cleanarchitecture.domain.interactors.GetTopUseCase
import com.cardinal.core.domain.exceptions.Failure
import com.cardinal.core.domain.interactor.UseCase

class HomeViewModel(private val getTopUseCase: UseCase<List<Recipe>, GetTopUseCase.Params>) : ViewModel() {

    val state = MutableLiveData<HomeState>().apply {
        this.value = HomeState.Loading
    }

    sealed class HomeState {
        object Loading: HomeState()
        object Error: HomeState()
        object Empty : HomeState()
        data class Success(val recipes: List<RecipeUiItem>): HomeState()
    }

    fun loadData() {
        getTopUseCase(viewModelScope, GetTopUseCase.Params(1)) { it.either(::handleError, ::handleSuccess)}
    }

    private fun handleSuccess(list: List<Recipe>) {
        when {
            list.isEmpty() -> state.value = HomeState.Empty
            list.isNotEmpty() -> state.value =
                HomeState.Success(mapToPresentation(list))
        }
    }

    private fun mapToPresentation(recipe: List<Recipe>): List<RecipeUiItem> = recipe.map {
        RecipeUiItem(
            it.id,
            it.title,
            it.image
        )
    }

    private fun handleError(error: Failure) {
        state.value = HomeState.Error
    }
}

data class RecipeUiItem(val id: String, val title: String, val imageUrl: String)
