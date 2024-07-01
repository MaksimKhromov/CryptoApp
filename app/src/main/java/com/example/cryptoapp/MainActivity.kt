package com.example.cryptoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this).get(CoinViewModel::class.java)
        viewModel.priceList.observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
        })

        viewModel.getDetailInfo("BTC").observe(this, Observer {
            Log.d("TEST_OF_LOADING_DATA", "Success in activity: $it")
        })
    }
}

