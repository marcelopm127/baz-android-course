package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.domain.entity.AskOrBid
import com.example.projectwizeline.domain.entity.PayloadOrderBook
import com.example.projectwizeline.domain.repository.CryptoRepository
import com.example.projectwizeline.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetOrderBooksUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    suspend operator fun invoke(idBook: String): Flow<Resource<PayloadOrderBook>> = flow {
        emit(Resource.Loading())

        val response = cryptoRepository.getOrderBook(idBook)

        if(response.error != null) {
            emit(Resource.Error(error = response.error))
        } else {
            val data = response.data
            val listAskAndBids = mutableListOf<AskOrBid>()
            listAskAndBids.addAll(
                data?.payloadOrderBook?.toPayloadOrderBook()?.asks ?: mutableListOf()
            )
            listAskAndBids.addAll(
                data?.payloadOrderBook?.toPayloadOrderBook()?.bids ?: mutableListOf()
            )

            val payloadOrderBook = PayloadOrderBook(
                asksAndBids = listAskAndBids
            )

            emit(Resource.Success(data = payloadOrderBook))
        }
    }
}