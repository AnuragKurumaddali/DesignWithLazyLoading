package com.raj.design.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raj.design.models.ProgramDO
import com.raj.design.services.RetrofitService

class ProgramViewModel : ViewModel() {

    private val programServiceResponse = RetrofitService()

    fun getProgramData() : MutableLiveData<List<ProgramDO>>?{
        return programServiceResponse.loadProgramData()
    }
}