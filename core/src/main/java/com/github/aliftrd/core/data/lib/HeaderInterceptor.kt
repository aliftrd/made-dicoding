package com.github.aliftrd.core.data.lib

import com.github.aliftrd.core.utils.ConstVar
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor(
    private val requestHeaders: HashMap<String, String>
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        mapRequestHeaders()

        val request = mapHeaders(chain)

        return chain.proceed(request)
    }

    private fun mapRequestHeaders() {
        val token = ConstVar.API_KEY
        requestHeaders["Authorization"] = "Bearer $token"
    }

    private fun mapHeaders(chain: Interceptor.Chain): Request {
        val original = chain.request()

        val requestBuilder = original.newBuilder()

        for ((key, value) in requestHeaders) {
            requestBuilder.addHeader(key, value)
        }
        return requestBuilder.build()
    }

}