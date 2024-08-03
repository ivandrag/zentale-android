package com.bedtime.stories.kids.zentale.networking

import com.bedtime.stories.kids.zentale.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class CustomInterceptor(
    private val auth: FirebaseAuth
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val shouldHaveAuthHeaders =
            originalRequest.header(SHOULD_HAVE_AUTH_HEADERS)?.toBoolean() ?: false

        return if (shouldHaveAuthHeaders) {
            val token = auth.currentUser?.getIdToken(false)?.result?.token
            println("token $token")

            val newRequest = originalRequest.newBuilder()
                .addHeader(AUTHORIZATION, "Bearer $token")
                .removeHeader(SHOULD_HAVE_AUTH_HEADERS)
                .build()

            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun provideOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    firebaseAuth: FirebaseAuth
): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    okHttpClient.connectTimeout(5, TimeUnit.MINUTES)
    okHttpClient.readTimeout(5, TimeUnit.MINUTES);
    okHttpClient.apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(httpLoggingInterceptor)
        }
        addInterceptor(CustomInterceptor(firebaseAuth))
    }
    return okHttpClient.build()
}

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = true
}

@OptIn(ExperimentalSerializationApi::class)
fun provideRetrofit(okHttpClient: OkHttpClient): ZentaleApi {
    val contentType = "application/json".toMediaType()
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    return retrofit.create(ZentaleApi::class.java)
}
