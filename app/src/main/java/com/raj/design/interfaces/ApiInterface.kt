package com.raj.design.interfaces

import com.raj.design.models.ChannelDO
import com.raj.design.models.ProgramDO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    //    https://demo-c.cdn.vmedia.ca/json/Channels
    @GET("json/Channels")
    fun getChannelsList(): Call<List<ChannelDO>>

    //    https://demo-c.cdn.vmedia.ca/json/ProgramItems
    @GET("json/ProgramItems")
    fun getProgramItemList(): Call<List<ProgramDO>>
}