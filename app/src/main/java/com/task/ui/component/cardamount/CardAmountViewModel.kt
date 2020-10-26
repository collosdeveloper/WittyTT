package com.task.ui.component.cardamount

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

class CardAmountViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {

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
}