package com.niraj.qfonapp.data.model.remote

sealed class NetworkResult {
    data class Success(val data: ArrayList<DataItem?>): NetworkResult()
    data class Error(val error: String): NetworkResult()
}
