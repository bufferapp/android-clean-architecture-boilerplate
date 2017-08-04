package org.buffer.android.boilerplate.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [BufferooService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object BufferooServiceFactory {

    fun makeBuffeoorService(): BufferooService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor())
        return makeBufferooService(okHttpClient, makeGson())
    }

    fun makeBufferooService(okHttpClient: OkHttpClient, gson: Gson): BufferooService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://joe-birch-dsdb.squarespace.com/s/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(BufferooService::class.java)
    }

    fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    fun makeGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
       // logging.level = if (BuildConfig.DEBUG)
         //   HttpLoggingInterceptor.Level.BODY
        //else
          //  HttpLoggingInterceptor.Level.NONE
        return logging
    }

}