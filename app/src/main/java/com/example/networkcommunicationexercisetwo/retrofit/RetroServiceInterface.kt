package com.example.networkcommunicationexercisetwo.retrofit

import com.example.networkcommunicationexercisetwo.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("v2/all")
    fun getCountryList(): Call<List<CountryModel>>
}