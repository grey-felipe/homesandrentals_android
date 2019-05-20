package com.bows.arrows.homesrentals.api

import com.bows.arrows.homesrentals.utilities.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", "")
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

    val instance: IApi by lazy {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(IApi::class.java)
    }
}


//    private val authInterceptor = Interceptor { chain ->
//        val original = chain.request()
//        val requestBuilder = original
//            .newBuilder()
//            .addHeader("Authorization", "")
//            .method(original.method(), original.body())
//        val request = requestBuilder.build()
//        chain.proceed(request)
//    }
//
//    private val okHttpClient = OkHttpClient()
//        .newBuilder()
//        .addInterceptor(authInterceptor)
//        .build()