package com.example.musicapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: 167038e30dmsh1477cce83237743p109b0bjsn26c549e44bf4" + "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : retrofit2.Call<MyData>
}