package com.task.di

import com.task.ui.component.addmoneywith.AddMoneyWithActivity
import com.task.ui.component.cardamount.CardAmountActivity
import com.task.ui.component.carddetails.CardDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector()
    abstract fun contributeAddMoneyWithActivity(): AddMoneyWithActivity

    @ContributesAndroidInjector()
    abstract fun contributeCardDetailsActivity(): CardDetailsActivity

    @ContributesAndroidInjector()
    abstract fun contributeCardAmountActivity(): CardAmountActivity
}
