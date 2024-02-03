package com.munny.lib

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class UnitCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        check(returnType is ParameterizedType) { "Call<T> must be a ParameterizedType" }

        val responseType = getParameterUpperBound(0, returnType)

        if (responseType != Unit::class.java) {
            return null
        }

        return UnitCallAdapter()
    }
}
