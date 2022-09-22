package com.example.projectwizeline.domain.repository

import com.example.projectwizeline.data.api.response.OrderBookResponse
import com.example.projectwizeline.data.api.response.TickerResponse
import com.example.projectwizeline.domain.entity.ResumeAvailableBook
import com.example.projectwizeline.util.Resource

interface CryptoRepository {
    fun getResumeAvailableBook(): Resource<List<ResumeAvailableBook>>

    suspend fun getTicker(book: String? = ""): Resource<TickerResponse>

    suspend fun getOrderBook(book: String? = ""): Resource<OrderBookResponse>
}