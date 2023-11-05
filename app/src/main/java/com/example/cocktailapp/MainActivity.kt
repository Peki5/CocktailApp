package com.example.cocktailapp

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


const val BASE_URL="https://www.thecocktaildb.com/"
private lateinit var textView: TextView
private lateinit var userRecyclerView:RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRecyclerView = findViewById(R.id.recyclerView)

        userRecyclerView.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        userRecyclerView.layoutManager=linearLayoutManager
//        textView = findViewById(R.id.txtDrink)
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder=Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<DrinkResponse>{
            override fun onResponse(call: Call<DrinkResponse>, response: Response<DrinkResponse>) {
                val responseBody=response.body()!!

                val drinks = responseBody.drinks

                myAdapter= MyAdapter(baseContext,drinks)
                myAdapter.notifyDataSetChanged()
                userRecyclerView.adapter=myAdapter

//                val myStringBuilder=StringBuilder()
//                for(myData in drinks){
//                    myStringBuilder.append(myData.strDrink)
//                    Log.d("MainActivity",myStringBuilder.toString())
//                    myStringBuilder.append("\n")
//                }
//
//                textView.text=myStringBuilder
            }

            override fun onFailure(call: Call<DrinkResponse>, t: Throwable) {
                Log.d("MainActivity","onFailure: "+t.message)
            }
        })
    }
}