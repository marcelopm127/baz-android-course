package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.data.api.response.ResumeAvailableBookResponse
import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.repository.CryptoRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class GetResumeAvailableBookUseCase @Inject constructor(private val cryptoRepository: CryptoRepository) {
    operator fun invoke(): Single<Response<ResumeAvailableBookResponse>> =
        cryptoRepository.getResumeAvailableBook()
}