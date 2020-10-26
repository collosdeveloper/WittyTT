package com.task.ui.component.carddetails

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.WindowManager
import com.braintreepayments.cardform.view.CardForm
import com.task.data.dto.paymenttype.CreditCard
import com.task.databinding.CarddetailsActivityBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.cardamount.CardAmountActivity
import com.task.utils.observe
import javax.inject.Inject


class CardDetailsActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var cardDetailViewModel: CardDetailsViewModel

    private lateinit var binding: CarddetailsActivityBinding

    override fun initViewBinding() {
        binding = CarddetailsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        cardDetailViewModel = viewModelFactory.create(cardDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    override fun observeViewModel() {
        observe(cardDetailViewModel.saveStatusLiveData, ::handleSaveResult)
        observe(cardDetailViewModel.lastCreditCardLiveData, ::handleLastSavingCreditCard)
    }

    private fun initViews() {
        binding.cardForm.cardRequired(true)
            .maskCardNumber(true)
            .maskCvv(true)
            .expirationRequired(true)
            .cvvRequired(true)
            .postalCodeRequired(false)
            .mobileNumberRequired(false)
            .saveCardCheckBoxChecked(false)
            .saveCardCheckBoxVisible(false)
            .cardholderName(CardForm.FIELD_DISABLED)
            .setup(this)
        binding.cardForm.cvvEditText.inputType = InputType.TYPE_CLASS_NUMBER

        // Warning: this is for development purposes only and should never be done outside of this example app.
        // Failure to set FLAG_SECURE exposes your app to screenshots allowing other apps to steal card information.
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)

        binding.continueBtn.setOnClickListener { onContinueClicked() }
        cardDetailViewModel.loadLastSavedCreditCard()
    }

    private fun handleLastSavingCreditCard(creditCard: CreditCard) {
        binding.cardForm.cardEditText.setText(creditCard.cardNumber)
        binding.cardForm.expirationDateEditText.setText("${creditCard.expirationMonth}${creditCard.expirationYear}")
        binding.cardForm.cvvEditText.setText(creditCard.cvv)
    }

    private fun onContinueClicked() {
        if (binding.cardForm.isValid) {
            cardDetailViewModel.setUserCardDetails(binding.cardForm.cardNumber, binding.cardForm.expirationMonth,
                binding.cardForm.expirationYear, binding.cardForm.cvv)
        } else {
            binding.cardForm.validate()
        }
    }

    private fun handleSaveResult(saveStatus: Boolean) {
        if(saveStatus) {
            navigateToAmountScreen()
        }
    }


    private fun navigateToAmountScreen() {
        val nextScreenIntent = Intent(this, CardAmountActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }
}