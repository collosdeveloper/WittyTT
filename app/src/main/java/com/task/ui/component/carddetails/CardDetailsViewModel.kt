package com.task.ui.component.carddetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.data.DataRepository
import com.task.data.dto.paymenttype.CreditCard
import com.task.ui.base.BaseViewModel
import com.task.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CardDetailsViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val saveStatusLiveDataPrivate = MutableLiveData<Boolean>()
    val saveStatusLiveData: LiveData<Boolean> get() = saveStatusLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val lastCreditCardLiveDataPrivate = MutableLiveData<CreditCard>()
    val lastCreditCardLiveData: LiveData<CreditCard> get() = lastCreditCardLiveDataPrivate

    fun loadLastSavedCreditCard() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepository.getLastUsedCreditCard().collect { result ->
                    result.data?.let { creditCard ->
                        lastCreditCardLiveDataPrivate.value = creditCard
                    }
                }
            }
        }
    }

    fun setUserCardDetails(
        cardNumber: String,
        expirationMonth: String,
        expirationYear: String,
        cvv: String
    ) {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepository.saveCreditCard(
                    CreditCard(
                        cardNumber,
                        expirationMonth,
                        expirationYear,
                        cvv
                    )
                ).collect { saveStatus ->
                    saveStatusLiveDataPrivate.value = saveStatus
                }
            }
        }
    }
}