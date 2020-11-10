package com.raj.design.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raj.design.models.ChannelDO
import com.raj.design.services.RetrofitService

class ChannelViewModel : ViewModel() {

    private val channelServiceResponse = RetrofitService()

    fun getChannelData() : MutableLiveData<List<ChannelDO>>?{
        return channelServiceResponse.loadChannelData()
    }
}