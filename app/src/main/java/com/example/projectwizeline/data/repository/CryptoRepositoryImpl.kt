package com.example.projectwizeline.data.repository

import com.example.projectwizeline.data.api.APIService
import com.example.projectwizeline.data.api.response.OrderBookResponse
import com.example.projectwizeline.data.api.response.ResumeAvailableBookResponse
import com.example.projectwizeline.data.api.response.TickerResponse
import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.repository.CryptoRepository
import com.example.projectwizeline.util.Resource
import io.reactivex.rxjava3.core.Single
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val api: APIService): CryptoRepository {

    override fun getResumeAvailableBook(): Single<Response<ResumeAvailableBookResponse>> {
        return api.getResumeAvailableBook()
    }

    override suspend fun getTicker(book: String?): Resource<TickerResponse> {

        return try {
            val response = api.getTicker(book)

            if(response.isSuccessful) {
                val ticker = response.body()
                Resource.Success(ticker)
            } else {
                response.errorBody()?.let {
                    Resource.Error(error = it.toString())
                } ?: Resource.Error(error = Constants.ERROR_UNKNOWN)
            }
        } catch (e: IOException) {
            Resource.Error(
                error = Constants.ERROR_INTERNET_CONNECTION
            )
        } catch (e: HttpException) {
            Resource.Error(
                error = Constants.ERROR_RESOURCE_SOURCE
            )
        }
    }

    override suspend fun getOrderBook(book: String?): Resource<OrderBookResponse> {
        return try {
            val response = api.getOrderBook(book)

            if(response.isSuccessful) {
                val orderBook = response.body()
                Resource.Success(orderBook)
            } else {
                response.errorBody()?.let {
                    Resource.Error(error = it.toString())
                } ?: Resource.Error(error = Constants.ERROR_UNKNOWN)
            }
        } catch (e: IOException) {
            Resource.Error(
                error = Constants.ERROR_INTERNET_CONNECTION
            )
        } catch (e: HttpException) {
            Resource.Error(
                error = Constants.ERROR_RESOURCE_SOURCE
            )
        }
    }
}