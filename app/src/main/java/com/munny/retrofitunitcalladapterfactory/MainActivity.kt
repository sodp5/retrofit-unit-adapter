package com.munny.retrofitunitcalladapterfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.munny.lib.UnitCallAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

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
