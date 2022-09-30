package com.example.projectwizeline.domain.repository

import com.example.projectwizeline.data.api.response.OrderBookResponse
import com.example.projectwizeline.data.api.response.ResumeAvailableBookResponse
import com.example.projectwizeline.data.api.response.TickerResponse
import com.example.projectwizeline.util.Resource
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface CryptoRepository {
    fun getResumeAvailableBook(): Single<Response<ResumeAvailableBookResponse>>

    suspend fun getTicker(book: String? = ""): Resource<TickerResponse>

    suspend fun getOrderBook(book: String? = ""): Resource<OrderBookResponse>
}