package com.prueba.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.prueba.R
import com.prueba.viewModel.InfoViewModel
import kotlinx.android.synthetic.main.activity_info.*

class InfoTeam : AppCompatActivity() {

    private var keyTeam: String =""
    private val viewModelInfo by lazy { ViewModelProvider(this).get(InfoViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val bundle=intent.extras
        if(bundle!=null) keyTeam = bundle.getString("idTeam")!!.toString()
        observerDataInFo()
    }

    private fun observerDataInFo(){

        viewModelInfo.fetchInfoData(keyTeam).observe(this, Observer {
            name.text = it[0].strTeam
            description.text = it[0].strDescriptionES
            foundation.text = it[0].intFormedYear
            Glide.with(this).load(it[0].strTeamBadge).into(badge)
            Glide.with(this).load(it[0].strTeamJersey).into(jersy)
            println(it[0].strDescriptionEN)
            println(it[0].idTeam)
            println(it[0].strWebsite)
            println(it[0].strFacebook)
            println(it[0].strTwitter)
            println(it[0].strInstagram)
        })
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean
    {
        if (menuItem.itemId == android.R.id.home) {
            val intent = Intent(this@InfoTeam, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

}
