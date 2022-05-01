package com.niraj.qfonapp.data.remote

import com.niraj.qfonapp.data.model.remote.GetPackageDetailsResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("get-package-details")
    suspend fun getPackageDetails() : GetPackageDetailsResponse
}