package com.prueba.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.data.model.Team
import com.prueba.data.network.Api
class MainViewModel: ViewModel() {

    private val api = Api()
    fun fetchTeamsData(): LiveData<MutableList<Team>>{
        val mutableData = MutableLiveData<MutableList<Team>>()
        api.getTeamData().observeForever{ teamList ->
            mutableData.value = teamList
        }
        return mutableData
    }
}