package com.task.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.ui.ViewModelFactory
import com.task.ui.component.addmoneywith.AddMoneyWithViewModel
import com.task.ui.component.cardamount.CardAmountViewModel
import com.task.ui.component.carddetails.CardDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Since dagger
 * support multibinding you are free to bind as may ViewModels as you want.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AddMoneyWithViewModel::class)
    abstract fun bindAddMoneyWithViewModel(viewModel: AddMoneyWithViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CardDetailsViewModel::class)
    abstract fun bindCardDetailsViewModel(viewModel: CardDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CardAmountViewModel::class)
    abstract fun bindCardAmountViewModel(viewModel: CardAmountViewModel): ViewModel
}
