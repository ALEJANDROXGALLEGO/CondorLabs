package com.prueba.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prueba.data.model.Team
import org.json.JSONObject
import java.io.IOException
import com.prueba.utils.Constants.Companion.BASE_URL
import okhttp3.*

class Api {

    var client = OkHttpClient()

    fun getTeamData():LiveData<MutableList<Team>>{
        val mutableData = MutableLiveData<MutableList<Team>>()
        val listDataTeam = mutableListOf<Team>()
        val events = JSONObject(run(BASE_URL)).getJSONArray("teams")
        for (i in 0 until events.length()) {
            val item = events.getJSONObject(i)
              if( item.get("strLeague").toString()=="Spanish La Liga") {
                  val team = Team(
                      item.get("idTeam").toString(),
                      item.get("strTeamBadge").toString(),
                      "Equipo: " + item.get("strTeam").toString(),
                      "Estadio: " + item.get("strStadium").toString()
                  )
                  listDataTeam.add(team)
              }
        }
        mutableData.value = listDataTeam
        return mutableData
    }

    @Throws(IOException::class)
    fun run(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body()!!.string() }
    }
}
