package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.data.api.entity.PayloadDto
import com.example.projectwizeline.data.api.response.ResumeAvailableBookResponse
import com.example.projectwizeline.domain.repository.CryptoRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetResumeAvailableBookUseCaseTest {

    private val repository: CryptoRepository = mockk()
    lateinit var getResumeAvailableBookUseCase: GetResumeAvailableBookUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        getResumeAvailableBookUseCase = GetResumeAvailableBookUseCase(repository)
    }

    @Test
    fun `when submit request with and return values from api`() {

        //GIVEN
        val mockData = Single.just(Response.success(ResumeAvailableBookResponse(listOf(PayloadDto(idBook = "btc_mxn", minimumPrice = "50", "500")))))
        every { repository.getResumeAvailableBook() } returns mockData

        //WHEN
        val result = getResumeAvailableBookUseCase()

        //THEN
        Assert.assertEquals(result, mockData)
    }
}