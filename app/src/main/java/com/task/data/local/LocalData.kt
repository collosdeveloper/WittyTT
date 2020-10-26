package com.task.data.local

import android.content.SharedPreferences
import com.task.*
import com.task.App.Companion.context
import com.task.data.Resource
import com.task.data.dto.paymenttype.CreditCard
import com.task.data.error.EMPTY_VALUE_ERROR
import javax.inject.Inject

class LocalData @Inject constructor() {

    fun saveCreditCard(creditCard: CreditCard): Boolean {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(LAST_CREDITCARD_KEY, creditCard.cardNumber)
        editor.putString(
            CREDITCARD_EXP_MONTH_KEY + creditCard.cardNumber,
            creditCard.expirationMonth
        )
        editor.putString(CREDITCARD_EXP_YEAR_KEY + creditCard.cardNumber, creditCard.expirationYear)
        editor.putString(CREDITCARD_CVV_KEY + creditCard.cardNumber, creditCard.cvv)
        editor.apply()
        return editor.commit()
    }

    fun getLastUsedCreditCard(): Resource<CreditCard> {
        context.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0).let { sharedPref ->
            sharedPref.getString(LAST_CREDITCARD_KEY, null)?.let { cardNumber ->
                return Resource.Success(
                    CreditCard(
                        cardNumber,
                        sharedPref.getString(CREDITCARD_EXP_MONTH_KEY + cardNumber, "")!!,
                        sharedPref.getString(CREDITCARD_EXP_YEAR_KEY + cardNumber, "")!!,
                        sharedPref.getString(CREDITCARD_CVV_KEY + cardNumber, "")!!
                    )
                )
            }
        }
        return Resource.DataError(EMPTY_VALUE_ERROR)
    }
}