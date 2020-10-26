package com.task.ui.component.cardamount

import android.content.Intent
import android.os.Bundle
import com.task.R
import com.task.data.dto.paymenttype.CreditCard
import com.task.databinding.CardamountActivityBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.addmoneywith.AddMoneyWithActivity
import com.task.ui.component.threeds.ThreeDSActivity
import com.task.utils.observe
import javax.inject.Inject

class CardAmountActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var cardAmountViewModel: CardAmountViewModel
    private lateinit var binding: CardamountActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnChange.setOnClickListener { onChangeClicked() }
        binding.btnAddMoney.setOnClickListener { onAddMoneyClicked() }

        cardAmountViewModel.loadLastSavedCreditCard()
    }

    override fun initViewBinding() {
        binding = CardamountActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        cardAmountViewModel = viewModelFactory.create(cardAmountViewModel::class.java)
    }

    override fun observeViewModel() {
        observe(cardAmountViewModel.lastCreditCardLiveData, ::handleLastSavingCreditCard)
    }

    private fun handleLastSavingCreditCard(creditCard: CreditCard) {
        binding.tvCardNumber.text = getString(R.string.visa_card_formatter, creditCard.cardNumber.takeLast(4))
    }

    private fun onAddMoneyClicked() {
        navigateToThreeDSScreen()
    }

    private fun navigateToThreeDSScreen() {
        val nextScreenIntent = Intent(this, ThreeDSActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    private fun onChangeClicked() {
        navigateAddMoneyWithScreen()
    }

    private fun navigateAddMoneyWithScreen() {
        val nextScreenIntent = Intent(this, AddMoneyWithActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }
}