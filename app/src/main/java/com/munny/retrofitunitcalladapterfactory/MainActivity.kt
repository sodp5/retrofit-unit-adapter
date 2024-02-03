package com.munny.retrofitunitcalladapterfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.munny.retrofit.adapter.unit.UnitCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Retrofit.Builder()
            .baseUrl("Your Url")
            .addConverterFactory(GsonConverterFactory.create())
            /* Add Here! */
            .addCallAdapterFactory(UnitCallAdapterFactory())
            .build()
    }
}
