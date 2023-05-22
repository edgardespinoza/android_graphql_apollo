package com.eespinor.fishtrade.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.eespinor.fishtrade.BuildConfig
import com.eespinor.fishtrade.data.ApolloProductRepository
import com.eespinor.fishtrade.domain.repository.ProductRepository
import com.eespinor.fishtrade.domain.usecase.GetProductUseCase
import com.eespinor.fishtrade.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    @Singleton
    fun providerApolloClient():ApolloClient{

        val interceptor = object : ApolloInterceptor  {
            override fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>> {
                return chain.proceed(request).onEach {
                    println("Received response for ${it.operation.name()}: ${it.data}")
                }
            }
        }

        return ApolloClient.Builder()
            .serverUrl("https://opdexasrumsmfdgggkfi.supabase.co/graphql/v1")
            .addHttpHeader("apikey", BuildConfig.API_KEY)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providerApolloProductRepository(apolloClient: ApolloClient): ProductRepository{
        return ApolloProductRepository(apolloClient)
    }

    @Provides
    @Singleton
    fun providerGetProductsUseCase(productRepository: ProductRepository):GetProductsUseCase{
        return GetProductsUseCase(productRepository)
    }

    @Provides
    @Singleton
    fun providerGetProductUseCase(productRepository: ProductRepository):GetProductUseCase{
        return GetProductUseCase(productRepository)
    }

}