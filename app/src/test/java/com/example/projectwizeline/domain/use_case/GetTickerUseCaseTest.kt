package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.data.api.entity.PayloadDetailDto
import com.example.projectwizeline.data.api.response.TickerResponse
import com.example.projectwizeline.domain.repository.CryptoRepository
import com.example.projectwizeline.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTickerUseCaseTest {

    private val repository: CryptoRepository = mockk()
    private lateinit var getTickerUseCase: GetTickerUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        getTickerUseCase = GetTickerUseCase(repository)
    }

    @Test
    fun `when submit data with a valid request and loading status is expected`() = runBlocking{
        //GIVEN
        val mockData = TickerResponse(PayloadDetailDto(last = "10", low = "1", high = "10"))
        coEvery { repository.getTicker("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getTickerUseCase("btc_mxn").first()

        //THEN
        Assert.assertTrue(result is Resource.Loading)
    }

    @Test
    fun `when submit data with a valid request and success status is expected`() = runBlocking{
        //GIVEN
        val mockData = TickerResponse(PayloadDetailDto(last = "10", low = "1", high = "10"))
        coEvery { repository.getTicker("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getTickerUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit data with a incorrect request and success status is expected`() = runBlocking{
        //GIVEN
        val mockData = TickerResponse(PayloadDetailDto(last = "9 ", low = "asd", high = "asd"))
        coEvery { repository.getTicker("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getTickerUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit null data request and success status is expected`() = runBlocking{
        //GIVEN
        val mockData = TickerResponse(PayloadDetailDto(last = null, low = null, high = null))
        coEvery { repository.getTicker("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getTickerUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit default data request and success status is expected`() = runBlocking{
        //GIVEN
        val mockData = TickerResponse(PayloadDetailDto())
        coEvery { repository.getTicker("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getTickerUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }
}