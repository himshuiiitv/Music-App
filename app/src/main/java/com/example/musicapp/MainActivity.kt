package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // if the API call is a success then this method is executed
//                val dataList = response.body()?.data!!
//
////                val textView=findViewById<TextView>(R.id.helloText)
////                textView.text=dataList.toString()
//
//                myAdapter= MyAdapter(this@MainActivity,dataList)
//                myRecyclerView.adapter=myAdapter
//                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
//                Log.d("TAG:onResponse", "onResponse: " + response.body())
                val responseData = response.body()
                if (responseData != null) {
                    val dataList = responseData.data ?: emptyList()
                    myAdapter = MyAdapter(this@MainActivity, dataList)
                    myRecyclerView.adapter = myAdapter
                    myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    Log.d("TAG:onResponse", "onResponse: " + response.body())
                } else {
                    Log.d("TAG:onResponse", "Response body is null")
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if the API call is a failure then this method is executed
                Log.d("TAG: onFailure", "onFailure: " + t.message)
            }
        }
        )
    }
}