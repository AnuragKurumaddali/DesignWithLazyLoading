package com.raj.design.models

import com.google.gson.annotations.SerializedName

class ChannelDO {
    @SerializedName("orderNum")
    var orderNum: Int? = null

    @SerializedName("accessNum")
    var accessNum: Int? = null

    @SerializedName("CallSign")
    var callSign: String? = null

    @SerializedName("id")
    var id: Int? = null

}