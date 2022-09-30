package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.domain.entity.PayloadDetail
import com.example.projectwizeline.domain.entity.Ticker
import com.example.projectwizeline.domain.repository.CryptoRepository
import com.example.projectwizeline.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    suspend operator fun invoke(idBook: String):Flow<Resource<Ticker>> = flow {
        emit(Resource.Loading())

        val response = cryptoRepository.getTicker(idBook)

        if(response.error != null) {
            emit(Resource.Error(error = response.error))
        } else {
            val data = response.data

            val ticker = Ticker(
                payloadDetail = PayloadDetail(
                    data?.payloadDetailDto?.last?.toDouble() ?: 0.0,
                    data?.payloadDetailDto?.low?.toDouble() ?: 0.0,
                    data?.payloadDetailDto?.high?.toDouble() ?: 0.0
                )
            )

            emit(Resource.Success(data = ticker))
        }
    }
}