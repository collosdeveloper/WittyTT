package com.task.di

import com.task.data.local.LocalData
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideLocalRepository(): LocalData {
        return LocalData()
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}
