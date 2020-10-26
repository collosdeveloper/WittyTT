package com.task.ui.component.addmoneywith

import android.content.Intent
import android.os.Bundle
import com.task.databinding.AddmoneywithActivityBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.carddetails.CardDetailsActivity
import javax.inject.Inject

class AddMoneyWithActivity : BaseActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var addMoneyWithViewModel: AddMoneyWithViewModel

    private lateinit var binding: AddmoneywithActivityBinding

    override fun initViewBinding() {
        binding = AddmoneywithActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun initializeViewModel() {
        addMoneyWithViewModel = viewModelFactory.create(addMoneyWithViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bankCardButton.setClickListener { navigateToMCardDetailScreen() }
    }

    private fun navigateToMCardDetailScreen() {
        val nextScreenIntent = Intent(this, CardDetailsActivity::class.java)
        startActivity(nextScreenIntent)
        finish()
    }

    override fun observeViewModel() {
    }
}