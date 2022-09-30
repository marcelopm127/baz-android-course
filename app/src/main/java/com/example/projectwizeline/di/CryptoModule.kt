package com.example.projectwizeline.di

import com.example.projectwizeline.data.api.APIClient
import com.example.projectwizeline.data.api.APIService
import com.example.projectwizeline.data.repository.CryptoRepositoryImpl
import com.example.projectwizeline.domain.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoModule {

    /*@Provides
    @Singleton
    fun provideRepository(apiClient: APIService): CryptoRepository {
        return CryptoRepositoryImpl(apiClient)
    }*/

    @Provides
    @Singleton
    fun provideApiClient(): APIService {
        return APIClient.client.create(APIService::class.java)
    }
}