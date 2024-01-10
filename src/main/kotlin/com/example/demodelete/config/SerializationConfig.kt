package com.example.demodelete.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder

@Configuration
class SerializationConfig {


    private val j = Json


    @Bean
    fun kotlinSerializationJsonEncoder() = KotlinSerializationJsonEncoder(j)

    @Bean
    fun kotlinSerializationJsonDecoder() = KotlinSerializationJsonDecoder(j)


}
