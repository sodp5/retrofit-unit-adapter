package com.munny.lib

import com.google.gson.Gson
import com.munny.lib.mock_server.MockServerResponseUtil
import com.munny.lib.mock_server.SuccessResponse
import com.munny.lib.mock_server.TestApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@OptIn(ExperimentalCoroutinesApi::class)
class UnitCallAdapterFactoryTest {
    private lateinit var server: MockWebServer
    private lateinit var api: TestApi
    private lateinit var apiWithoutUnitCallAdapter: TestApi

    @Before
    fun setup() {
        server = MockWebServer()

        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(UnitCallAdapterFactory())
            .baseUrl(server.url("/"))
            .build()
            .create<TestApi>()

        apiWithoutUnitCallAdapter = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(server.url("/"))
            .build()
            .create<TestApi>()
    }

    @Test
    fun `Success 204 response`() = runTest {
        val response = MockServerResponseUtil.response204

        server.enqueue(response)

        assertDoesNotThrow {
            api.get204()
        }
    }

    @Test
    fun `Throw NPE if without UnitCallAdapterFactory`() = runTest {
        val response = MockServerResponseUtil.response204

        server.enqueue(response)

        assertThrows<NullPointerException> {
            apiWithoutUnitCallAdapter.get204()
        }
    }

    @Test
    fun `No effect on the 200 response`() = runTest {
        val response = MockServerResponseUtil.response200

        server.enqueue(response)

        val actualResponse = api.get200()

        val body = response.getBody()!!
        val bodyString = body.readUtf8()
        val expectedResponse = Gson().fromJson(bodyString, SuccessResponse::class.java)

        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `No effect on the 404 response`() = runTest {
        val response = MockServerResponseUtil.response404

        server.enqueue(response)

        val result = runCatching {
            api.error()
        }.exceptionOrNull()

        checkNotNull(result)

        val httpException = result as HttpException
        assertEquals(httpException.code(), 404)
    }

    @Test
    fun `No effect on the 500 response`() = runTest {
        val response = MockServerResponseUtil.response500

        server.enqueue(response)

        val result = runCatching {
            api.error()
        }.exceptionOrNull()

        checkNotNull(result)

        val httpException = result as HttpException
        assertEquals(httpException.code(), 500)
    }
}
