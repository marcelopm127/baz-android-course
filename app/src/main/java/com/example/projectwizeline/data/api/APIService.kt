package com.example.projectwizeline.data.api

import com.example.projectwizeline.data.api.APIEndPoint.WS_ORDER_BOOK
import com.example.projectwizeline.data.api.APIEndPoint.WS_RESUME_AVAILABLE_BOOK
import com.example.projectwizeline.data.api.APIEndPoint.WS_TICKER
import com.example.projectwizeline.data.api.response.OrderBookResponse
import com.example.projectwizeline.data.api.response.ResumeAvailableBookResponse
import com.example.projectwizeline.data.api.response.TickerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(WS_RESUME_AVAILABLE_BOOK)
    fun getResumeAvailableBook(): Single<Response<ResumeAvailableBookResponse>>
    @GET(WS_TICKER)
    suspend fun getTicker(@Query("book") book: String?): Response<TickerResponse>
    @GET(WS_ORDER_BOOK)
    suspend fun getOrderBook(@Query("book") book: String?): Response<OrderBookResponse>
}