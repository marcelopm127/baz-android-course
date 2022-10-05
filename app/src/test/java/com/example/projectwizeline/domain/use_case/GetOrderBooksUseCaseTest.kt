package com.example.projectwizeline.domain.use_case

import com.example.projectwizeline.data.api.entity.AskOrBidDto
import com.example.projectwizeline.data.api.entity.PayloadOrderBookDto
import com.example.projectwizeline.data.api.response.OrderBookResponse
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

class GetOrderBooksUseCaseTest {

    private val repository: CryptoRepository = mockk()
    private lateinit var getOrderBooksUseCase: GetOrderBooksUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)

        getOrderBooksUseCase = GetOrderBooksUseCase(repository)
    }

    @Test
    fun `when submit data with a valid request and loading status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "10", "0.12"),
            AskOrBidDto("btc_mxn", "12", "0.16")
        )
        val mockListBid: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "100", "0.120"),
            AskOrBidDto("btc_mxn", "120", "0.160")
        )
        val mockData = OrderBookResponse(PayloadOrderBookDto(mockListAsk, mockListBid))
        coEvery { repository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getOrderBooksUseCase("btc_mxn").first()

        //THEN
        Assert.assertTrue(result is Resource.Loading)
    }

    @Test
    fun `when submit data with a valid request and success status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "10", "0.12"),
            AskOrBidDto("btc_mxn", "12", "0.16")
        )
        val mockListBid: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "100", "0.120"),
            AskOrBidDto("btc_mxn", "120", "0.160")
        )
        val mockData = OrderBookResponse(PayloadOrderBookDto(mockListAsk, mockListBid))
        coEvery { repository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getOrderBooksUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit data with a incorrect request and success status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "a", "aa"),
            AskOrBidDto("btc_mxn", "b", "bb")
        )
        val mockListBid: List<AskOrBidDto> = listOf(
            AskOrBidDto("btc_mxn", "c", "cc"),
            AskOrBidDto("btc_mxn", "d", "dd")
        )
        val mockData = OrderBookResponse(PayloadOrderBookDto(mockListAsk, mockListBid))
        coEvery { repository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getOrderBooksUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit null data request and success status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<AskOrBidDto> = listOf(
            AskOrBidDto(null, null, null),
            AskOrBidDto(null, null, null)
        )
        val mockListBid: List<AskOrBidDto> = listOf(
            AskOrBidDto(null, null, null),
            AskOrBidDto(null, null, null)
        )
        val mockData = OrderBookResponse(PayloadOrderBookDto(mockListAsk, mockListBid))
        coEvery { repository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getOrderBooksUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `when submit default data request and success status is expected`() = runBlocking{
        //GIVEN
        val mockListAsk: List<AskOrBidDto> = listOf(
            AskOrBidDto(),
            AskOrBidDto()
        )
        val mockListBid: List<AskOrBidDto> = listOf(
            AskOrBidDto(),
            AskOrBidDto()
        )
        val mockData = OrderBookResponse(PayloadOrderBookDto(mockListAsk, mockListBid))
        coEvery { repository.getOrderBook("btc_mxn") } returns Resource.Success(mockData)

        //WHEN
        val result = getOrderBooksUseCase("btc_mxn").last()

        //THEN
        Assert.assertTrue(result is Resource.Success)
    }
}