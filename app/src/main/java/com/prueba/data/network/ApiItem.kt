package com.prueba.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prueba.data.model.Info
import org.json.JSONObject
import java.io.IOException
import com.prueba.utils.Constants.Companion.BASE_URL_INFO
import okhttp3.*

class ApiItem {

    var client = OkHttpClient()

    fun getTeamDataInfo(value: String):LiveData<MutableList<Info>>{
        val mutableDataInfo = MutableLiveData<MutableList<Info>>()
        val listDataInfo = mutableListOf<Info>()
        val urlInfo = BASE_URL_INFO + value
        val events = JSONObject(run(urlInfo)).getJSONArray("teams")

        if (events.length()==1) {
            val item = events.getJSONObject(0)
            val info = Info(
                item.get("idTeam").toString(),
                item.get("strTeamBadge").toString(),
                item.get("strTeam").toString(),
                item.get("strDescriptionES").toString(),
                item.get("strDescriptionEN").toString(),
                item.get("intFormedYear").toString(),
                item.get("strTeamJersey").toString(),
                item.get("strWebsite").toString(),
                item.get("strFacebook").toString(),
                item.get("strTwitter").toString(),
                item.get("strInstagram").toString(),
            )
            listDataInfo.add(info)
            mutableDataInfo.value = listDataInfo
        }
        return mutableDataInfo
    }

    @Throws(IOException::class)
    fun run(url: String): String? {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response -> return response.body()!!.string() }
    }
}
