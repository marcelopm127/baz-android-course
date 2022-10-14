package com.example.projectwizeline.app.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectwizeline.domain.entity.OrderBook
import com.example.projectwizeline.domain.entity.PayloadDetail
import com.example.projectwizeline.domain.entity.PayloadOrderBook
import com.example.projectwizeline.domain.entity.Ticker
import com.example.projectwizeline.domain.use_case.GetOrderBooksUseCase
import com.example.projectwizeline.domain.use_case.GetResumeAvailableBookUseCase
import com.example.projectwizeline.domain.use_case.GetTickerUseCase
import com.example.projectwizeline.util.Resource
import com.example.projectwizeline.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCryptoViewModel @Inject constructor(private val getTickerUseCase: GetTickerUseCase, private val getOrderBooksUseCase: GetOrderBooksUseCase)
    : ViewModel() {

    private val _detailTicker = MutableLiveData<Ticker>()
    val detail: LiveData<Ticker> get() = _detailTicker

    private val _orderBook = MutableLiveData<PayloadOrderBook>()
    val orderBook: LiveData<PayloadOrderBook> get() = _orderBook

    fun getDetailTicket(idBook: String) {
        viewModelScope.launch {
            val response = getTickerUseCase.invoke(idBook)

            response.collect {
                when(it) {
                    is Resource.Loading -> {
                        Utils.showLoadingDialog()
                    }

                    is Resource.Success -> {
                        _detailTicker.value = it.data ?: Ticker(PayloadDetail())
                        getOrderBook(idBook)
                    }

                    is Resource.Error -> {
                        Utils.hideLoadingDialog()
                    }
                }
            }
        }
    }

    fun getOrderBook(idBook: String) {
        viewModelScope.launch {
            val response = getOrderBooksUseCase.invoke(idBook)

            response.collect {
                when(it) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        _orderBook.value = it.data ?: PayloadOrderBook()
                        Utils.hideLoadingDialog()
                    }

                    is Resource.Error -> {
                        Utils.hideLoadingDialog()
                    }
                }
            }
        }
    }
}