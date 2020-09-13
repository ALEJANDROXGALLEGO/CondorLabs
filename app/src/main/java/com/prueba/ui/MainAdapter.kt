package com.prueba.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prueba.R
import com.prueba.data.model.Team
import kotlinx.android.synthetic.main.activity_main_item.view.*

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = mutableListOf<Team>()

    fun setListData(data: MutableList<Team>){
        dataList=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_main_item, parent, false)
        return  MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val team = dataList[position]
        holder.bindView(team)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, InfoTeam::class.java)
            intent.putExtra("idTeam", it.tag.toString())
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if(dataList.size>0){
            dataList.size
        }else{
            0
        }
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(team: Team){
            Glide.with(context).load(team.strTeamBadge).into(itemView.logo)
            itemView.name.text = team.strTeam
            itemView.stadium.text = team.strStadium
            itemView.tag = team.idTeam.toInt()
    }
}

}