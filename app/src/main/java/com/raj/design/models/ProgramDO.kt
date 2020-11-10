package com.raj.design.models

import com.google.gson.annotations.SerializedName

class ProgramDO {
    @SerializedName("startTime")
    private var startTime: String? = null

    @SerializedName("recentAirTime")
    private var recentAirTime: RecentAirTime? = null

    @SerializedName("length")
    private var length: Int? = null

    @SerializedName("name")
    private var name: String? = null

    fun getStartTime(): String? {
        return startTime
    }

    fun setStartTime(startTime: String?) {
        this.startTime = startTime
    }

    fun getRecentAirTime(): RecentAirTime? {
        return recentAirTime
    }

    fun setRecentAirTime(recentAirTime: RecentAirTime?) {
        this.recentAirTime = recentAirTime
    }

    fun getLength(): Int? {
        return length
    }

    fun setLength(length: Int?) {
        this.length = length
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
}