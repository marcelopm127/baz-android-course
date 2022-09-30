package com.example.projectwizeline.di

import com.example.projectwizeline.data.repository.CryptoRepositoryImpl
import com.example.projectwizeline.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CryptoBinds {

    @Binds
    fun bindRepository(repository: CryptoRepositoryImpl): CryptoRepository
}