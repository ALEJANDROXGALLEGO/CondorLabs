package com.prueba.ui

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.R
import com.prueba.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private val viewModelTeam by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        adapter= MainAdapter(this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        observerDataTeam()
    }

    private fun observerDataTeam(){
        viewModelTeam.fetchTeamsData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}