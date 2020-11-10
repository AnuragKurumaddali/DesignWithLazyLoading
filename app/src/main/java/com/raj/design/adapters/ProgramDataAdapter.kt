package com.raj.design.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raj.design.R
import com.raj.design.models.ProgramDO
import kotlinx.android.synthetic.main.program_list_item.view.*
import kotlinx.android.synthetic.main.time_child_list_item.view.*

class ProgramDataAdapter(private val isTimeCell:Boolean,private val lsPrograms : List<ProgramDO>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            VIEW_TYPE_NORMAL -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.program_list_item, parent, false)
            )
            VIEW_TYPE_TIME -> ViewHolderTime(
                LayoutInflater.from(parent.context).inflate(R.layout.time_child_list_item, parent, false)
            )
            else ->
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.program_list_item, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isTimeCell)
            VIEW_TYPE_TIME
        else
            VIEW_TYPE_NORMAL
    }

    override fun getItemCount(): Int {
        return lsPrograms.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        /*when(position){
            VIEW_TYPE_NORMAL ->
                setProgramName(holder as ViewHolder,position)
            VIEW_TYPE_TIME ->
                setTimeValue(holder as ViewHolderTime,position)
            else ->
                setProgramName(holder as ViewHolder,position)
        }*/
        if(isTimeCell)
            setTimeValue(holder as ViewHolderTime,position)
        else
            setProgramName(holder as ViewHolder,position)
    }

    fun setProgramName(holder: ViewHolder,position: Int){
        val programDO = lsPrograms[position]
        holder.tv_ProgramName.text = programDO.getName()
    }

    fun setTimeValue(holder: ViewHolderTime,position: Int){
        val programDO = lsPrograms[position]
        holder.tv_Time.text = programDO.getStartTime()
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tv_ProgramName : TextView = itemView.tv_ProgramName
    }

    inner class ViewHolderTime(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tv_Time : TextView = itemView.tv_Time
    }

    companion object {
        private const val VIEW_TYPE_TIME = 0
        private const val VIEW_TYPE_NORMAL = 1
    }

}