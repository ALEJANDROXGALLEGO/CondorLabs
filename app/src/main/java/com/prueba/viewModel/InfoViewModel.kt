package com.prueba.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.data.model.Info
import com.prueba.data.network.ApiItem

class InfoViewModel: ViewModel() {
    private val apiItem = ApiItem()
    fun fetchInfoData( value : String): LiveData<MutableList<Info>>{
        val mutableDataInfo = MutableLiveData<MutableList<Info>>()
        apiItem.getTeamDataInfo( value ).observeForever{ teamList ->
            mutableDataInfo.value = teamList
        }
        return mutableDataInfo
    }
}