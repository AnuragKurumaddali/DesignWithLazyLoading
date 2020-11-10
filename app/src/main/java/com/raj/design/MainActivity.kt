package com.raj.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raj.design.adapters.ChannelDataAdapter
import com.raj.design.models.ChannelDO
import com.raj.design.models.ProgramDO
import com.raj.design.viewmodels.ChannelViewModel
import com.raj.design.viewmodels.ProgramViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var lsChannels : List<ChannelDO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getChannelData()
        getProgramData()
    }

    private fun getChannelData(){

        val channelViewModel = ViewModelProviders.of(this).get(ChannelViewModel::class.java)

        channelViewModel.getChannelData()?.observe(this, Observer{
//                Log.e("aaa","Channel Data = "+it)
                lsChannels = it
        })
    }

    private fun getProgramData(){

        val programViewModel = ViewModelProviders.of(this).get(ProgramViewModel::class.java)

        programViewModel.getProgramData()?.observe(this, Observer{
//            Log.e("aaa","Program Data = "+it)
            initRecycler(it)
        })
    }


    private fun initRecycler(lsPrograms:List<ProgramDO>){

        rv_ChannelList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,
                RecyclerView.VERTICAL, false)
            adapter = ChannelDataAdapter(lsChannels,lsPrograms)
        }

    }
}