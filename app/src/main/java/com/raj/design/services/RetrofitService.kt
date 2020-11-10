package com.raj.design.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.raj.design.interfaces.ApiInterface
import com.raj.design.models.ChannelDO
import com.raj.design.models.ProgramDO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

        private const val BASE_URL = "https://demo-c.cdn.vmedia.ca/"
    }

    fun loadChannelData() : MutableLiveData<List<ChannelDO>>?{
        val  liveChannelResponse : MutableLiveData<List<ChannelDO>> = MutableLiveData()

        val retrofitCall = create().getChannelsList()

        retrofitCall.enqueue(object : Callback<List<ChannelDO>> {
            override fun onFailure(call: Call<List<ChannelDO>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }
            override fun onResponse(call: Call<List<ChannelDO>>, response: retrofit2.Response<List<ChannelDO>>) {
                liveChannelResponse.value = response.body()
            }
        })

        return liveChannelResponse
    }

    fun loadProgramData() : MutableLiveData<List<ProgramDO>>?{
        val  liveProgramResponse : MutableLiveData<List<ProgramDO>> = MutableLiveData()

        val retrofitCall = create().getProgramItemList()

        retrofitCall.enqueue(object : Callback<List<ProgramDO>> {
            override fun onFailure(call: Call<List<ProgramDO>>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }
            override fun onResponse(call: Call<List<ProgramDO>>, response: retrofit2.Response<List<ProgramDO>>) {
                liveProgramResponse.value = response.body()
            }
        })

        return liveProgramResponse
    }
}