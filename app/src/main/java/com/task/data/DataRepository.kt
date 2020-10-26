package com.task.data

import com.task.data.dto.paymenttype.CreditCard
import com.task.data.local.LocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val localRepository: LocalData,
                                         private val ioDispatcher: CoroutineContext) : DataRepositorySource {
    override suspend fun saveCreditCard(
        creditCard: CreditCard
    ): Flow<Boolean> {
        return flow {
            emit(localRepository.saveCreditCard(creditCard))
        }.flowOn(ioDispatcher)
    }

    override suspend fun getLastUsedCreditCard(): Flow<Resource<CreditCard>> {
        return flow {
            emit(localRepository.getLastUsedCreditCard())
        }.flowOn(ioDispatcher)
    }
}
