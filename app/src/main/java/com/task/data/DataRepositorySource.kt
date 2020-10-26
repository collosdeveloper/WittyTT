package com.task.data

import com.task.data.dto.paymenttype.CreditCard
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun saveCreditCard(creditCard: CreditCard): Flow<Boolean>
    suspend fun getLastUsedCreditCard(): Flow<Resource<CreditCard>>
}