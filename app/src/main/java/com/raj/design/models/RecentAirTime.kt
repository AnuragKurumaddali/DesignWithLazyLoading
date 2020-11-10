package com.raj.design.models

import com.google.gson.annotations.SerializedName

class RecentAirTime {
    @SerializedName("id")
    private var id: Int? = null

    @SerializedName("channelID")
    private var channelID: Int? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getChannelID(): Int? {
        return channelID
    }

    fun setChannelID(channelID: Int?) {
        this.channelID = channelID
    }
}