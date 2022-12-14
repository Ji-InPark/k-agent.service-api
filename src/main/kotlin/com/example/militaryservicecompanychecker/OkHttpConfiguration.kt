package com.example.militaryservicecompanychecker

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class OkHttpConfiguration {
    @Bean("okHttpClient")
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder().apply {
                connectTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
                readTimeout(10, TimeUnit.SECONDS)
            }.build()
    }
}