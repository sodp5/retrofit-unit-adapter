package com.munny.retrofit.adapter.unit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

internal class UnitCallAdapter : CallAdapter<Unit, Call<Unit>> {
    override fun responseType(): Type = Unit::class.java

    override fun adapt(call: Call<Unit>): Call<Unit> {
        return UnitCall(call)
    }

    private class UnitCall(
        private val delegate: Call<Unit>,
    ) : Call<Unit> by delegate {
        override fun enqueue(callback: Callback<Unit>) {
            delegate.enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        callback.onResponse(this@UnitCall, Response.success(Unit))
                    } else {
                        callback.onResponse(this@UnitCall, response)
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    callback.onFailure(call, t)
                }
            })
        }
    }
}
