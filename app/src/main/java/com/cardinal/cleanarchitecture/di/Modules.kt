package com.cardinal.cleanarchitecture.di

import com.cardinal.cleanarchitecture.data.FoodRepository
import com.cardinal.cleanarchitecture.data.source.FoodApi
import com.cardinal.cleanarchitecture.data.source.NetworkSource
import com.cardinal.cleanarchitecture.domain.entities.Recipe
import com.cardinal.cleanarchitecture.domain.gateway.FoodGateway
import com.cardinal.cleanarchitecture.domain.interactors.GetTopUseCase
import com.cardinal.cleanarchitecture.view.home.HomeViewModel
import com.cardinal.core.domain.interactor.UseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val repositoryModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().addQueryParameter("key", "").build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<FoodApi> {
        Retrofit.Builder()
            .baseUrl("https://www.food2fork.com/api/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
            .create(FoodApi::class.java)
    }

    single {
        NetworkSource(get())
    }

    single<FoodGateway> {
        FoodRepository(get())
    }
}

val domainModule = module {
    single { GetTopUseCase(get()) as UseCase<List<Recipe>, GetTopUseCase.Params> }
}

val viewModule = module {
    viewModel { HomeViewModel(get()) }
}