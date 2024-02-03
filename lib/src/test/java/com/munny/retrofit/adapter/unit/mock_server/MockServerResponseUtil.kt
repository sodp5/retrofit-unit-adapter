package com.munny.retrofit.adapter.unit.mock_server

import okhttp3.mockwebserver.MockResponse

object MockServerResponseUtil {
    val response200 = MockResponse()
        .setResponseCode(200)
        .setBody("{\"data\":\"anything\"}")

    val response204 = MockResponse()
        .setResponseCode(204)

    val response404 = MockResponse()
        .setResponseCode(404)
        .setBody("{ reason:\"bad request\" }")

    val response500 = MockResponse()
        .setResponseCode(500)
        .setBody("{ reason:\"internal server error\" }")
}
