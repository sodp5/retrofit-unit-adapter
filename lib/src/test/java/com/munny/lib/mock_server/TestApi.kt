package com.munny.lib.mock_server

import retrofit2.http.GET

interface TestApi {
    @GET("/get")
    suspend fun get200(): SuccessResponse

    @GET("/no-content")
    suspend fun get204()

    @GET("/error")
    suspend fun error()
}
