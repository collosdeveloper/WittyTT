package com.task.ui.component.threeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.databinding.ThreedsActivityBinding

class ThreeDSActivity : AppCompatActivity(){
    private lateinit var binding: ThreedsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThreedsActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}