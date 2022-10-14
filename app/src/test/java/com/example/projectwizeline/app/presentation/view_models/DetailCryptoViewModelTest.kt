package com.example.projectwizeline.app.presentation.view_models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.projectwizeline.domain.entity.AskOrBid
import com.example.projectwizeline.domain.entity.PayloadDetail
import com.example.projectwizeline.domain.entity.PayloadOrderBook
import com.example.projectwizeline.domain.entity.Ticker
import com.example.projectwizeline.domain.use_case.GetOrderBooksUseCase
import com.example.projectwizeline.domain.use_case.GetTickerUseCase
import com.example.projectwizeline.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailCryptoViewModelTest {
    private var getTickerUseCase: GetTickerUseCase = mockk()
    private var getOrderBooksUseCase: GetOrderBooksUseCase = mockk()

    private lateinit var detailCryptoViewModel: DetailCryptoViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailCryptoViewModel = DetailCryptoViewModel(getTickerUseCase, getOrderBooksUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a correct format value from getDetailTicker method`() = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockData = Ticker(PayloadDetail(last = 10.0, low = 1.0, high = 10.0))
        coEvery { getTickerUseCase(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getDetailTicket(idBookMock)

        //Then
        assert(detailCryptoViewModel.detail.value == mockData)
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a default format value from getDetailTicker method`() = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockData = Ticker(PayloadDetail())
        coEvery { getTickerUseCase(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getDetailTicket(idBookMock)

        //Then
        assert(detailCryptoViewModel.detail.value == mockData)
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a correct format value from getOrderBook method`() = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockListAskAndBids: List<AskOrBid> = listOf(
            AskOrBid("btc_mxn", 10.0, 0.12, "ASK"),
            AskOrBid("btc_mxn", 12.0, 0.16, "BID")
        )
        val mockData = PayloadOrderBook(
            asksAndBids = mockListAskAndBids
        )
        coEvery { getOrderBooksUseCase(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getOrderBook(idBookMock)

        //Then
        assert(detailCryptoViewModel.orderBook.value == mockData)
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a default format value from getOrderBook method`() = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockData = PayloadOrderBook()
        coEvery { getOrderBooksUseCase(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getOrderBook(idBookMock)

        //Then
        assert(detailCryptoViewModel.orderBook.value == mockData)
    }
}