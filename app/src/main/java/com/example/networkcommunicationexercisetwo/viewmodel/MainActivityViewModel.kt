package com.example.networkcommunicationexercisetwo.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.networkcommunicationexercisetwo.data.CountryModel
import com.example.networkcommunicationexercisetwo.retrofit.RetroServiceInterface
import com.example.networkcommunicationexercisetwo.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()

    fun getLiveDataObserver():MutableLiveData<List<CountryModel>>{
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetrofitInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCountryList()
        call.enqueue(object :retrofit2.Callback<List<CountryModel>>{
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
            }
        })
    }
}