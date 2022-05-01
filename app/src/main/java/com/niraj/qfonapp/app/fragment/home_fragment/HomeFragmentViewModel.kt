package com.niraj.qfonapp.app.fragment.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niraj.qfonapp.app.adapter.PackageAdapter
import com.niraj.qfonapp.data.model.remote.DataItem
import com.niraj.qfonapp.data.model.remote.NetworkResult
import com.niraj.qfonapp.data.remote.ApiInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val apiInterface: ApiInterface): ViewModel() {
    init {
        fetchPacketDetails()
    }

    var packageAdapter: PackageAdapter ?= null

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var _movieVideos = MutableLiveData<ArrayList<DataItem?>>()
    val packageListModel: LiveData<ArrayList<DataItem?>> get() = _movieVideos

    private fun fetchPacketDetails() {
        viewModelScope.launch(Dispatchers.Main) {
            when (val movies = getPackageDetails()){
                is NetworkResult.Success -> {
                    _movieVideos.value = movies.data
                }
                is NetworkResult.Error -> {
                    _error.value = movies.error
                }
            }
        }
    }

    private suspend fun getPackageDetails(): NetworkResult {
        return try {
            val fetchPacketDetails = apiInterface.getPackageDetails()
            fetchPacketDetails.data ?: throw Exception("No data found")
            NetworkResult.Success(fetchPacketDetails.data)
        }catch (ex: Exception){
            NetworkResult.Error(ex.message.toString())
        }
    }
}