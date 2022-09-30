package com.example.projectwizeline.app.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.entity.Payload
import com.example.projectwizeline.domain.entity.ResumeAvailableBook
import com.example.projectwizeline.domain.use_case.GetResumeAvailableBookUseCase
import com.example.projectwizeline.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ResumeCryptoViewModel @Inject constructor(private val getResumeAvailableBookUseCase: GetResumeAvailableBookUseCase)
    : ViewModel() {

    private val _resumeCryptos = MutableLiveData<ResumeAvailableBook>()
    val resumeCryptos: LiveData<ResumeAvailableBook> get() = _resumeCryptos

    private val disposable = CompositeDisposable()

    fun getResumeCryptos() {
        Utils.showLoadingDialog()

        disposable.add(
            getResumeAvailableBookUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ onSuccess, onError ->
                    onSuccess.let { response ->
                        if(response.isSuccessful) {
                            response.let {
                                Utils.hideLoadingDialog()

                                val filteredList = it.body()?.payloadDto?.filter { currency ->
                                    currency.idBook?.contains(Constants.MXN) == true
                                }

                                _resumeCryptos.value = ResumeAvailableBook(
                                    payload = filteredList?.map { payload ->
                                     Payload(
                                        idBook = payload.idBook,
                                        minimumPrice = payload.minimumPrice?.toDouble(),
                                        maximumPrice = payload.maximumPrice?.toDouble()
                                    )
                                })
                            }
                            onError?.let {
                                Utils.hideLoadingDialog()

                                _resumeCryptos.value = ResumeAvailableBook()
                            }
                        }
                    }
                }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}