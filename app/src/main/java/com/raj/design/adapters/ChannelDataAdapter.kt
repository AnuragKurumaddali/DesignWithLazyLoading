package com.raj.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raj.design.R
import com.raj.design.models.ChannelDO
import com.raj.design.models.ProgramDO
import kotlinx.android.synthetic.main.channel_list_item.view.*
import kotlinx.android.synthetic.main.time_list_item.view.*

class ChannelDataAdapter(private val lsChannels : List<ChannelDO>,private val lsPrograms : List<ProgramDO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.channel_list_item, parent, false)
            )
            VIEW_TYPE_TIME -> ViewHolderTime(
                LayoutInflater.from(parent.context).inflate(R.layout.time_list_item, parent, false)
            )
            else ->
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.channel_list_item, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return lsChannels.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when(position){
            VIEW_TYPE_NORMAL ->
                setProgramView(holder as ViewHolder,position)
            VIEW_TYPE_TIME ->
                setTimeView(holder as ViewHolderTime,position)
            else ->
                setProgramView(holder as ViewHolder,position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            VIEW_TYPE_TIME
         else
            VIEW_TYPE_NORMAL
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.rv_ProgramView
        val textView:TextView = itemView.tv_ChannelName
    }
    inner class ViewHolderTime(itemView : View) : RecyclerView.ViewHolder(itemView){
        val recyclerView : RecyclerView = itemView.rv_TimeView
        val textView:TextView = itemView.tv_DateTime
    }

    private fun getChannelWisePrograms(channelId : Int?) : List<ProgramDO> {
        var lsProgram : List<ProgramDO> = ArrayList<ProgramDO>()
        lsProgram = lsPrograms.filter {
            it.getRecentAirTime()?.getChannelID() == channelId
        }
        return lsProgram
    }

    private fun setProgramView(holder: ViewHolder,
                               position: Int){
        val channelDO = lsChannels[position]
        holder.textView.text = channelDO.id.toString()
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = ProgramDataAdapter(false,getChannelWisePrograms(channelDO.id))
            setRecycledViewPool(viewPool)
        }
    }
    private fun setTimeView(holder: ViewHolderTime,
                            position: Int){
        val programDO = lsPrograms[position]
        holder.textView.text = programDO.getStartTime()
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context, RecyclerView.HORIZONTAL, false)
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = ProgramDataAdapter(true,lsPrograms)
            setRecycledViewPool(viewPool)
        }
    }

    companion object {
        private const val VIEW_TYPE_TIME = 0
        private const val VIEW_TYPE_NORMAL = 1
    }
}