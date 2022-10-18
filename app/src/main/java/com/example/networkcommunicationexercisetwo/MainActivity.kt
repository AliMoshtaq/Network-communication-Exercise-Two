package com.example.networkcommunicationexercisetwo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkcommunicationexercisetwo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        val countryListRecyclerView = findViewById<RecyclerView>(R.id.countryListRecyclerview)
        countryListRecyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        countryListRecyclerView.adapter = recyclerAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        val viewModel: MainActivityViewModel =
            ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerAdapter.setCountryList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }
}










