package com.example.demodelete.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder

@Configuration
class SerializationConfig {

    @Bean
    fun jsonSerializer() = Json {

    }

    @Bean
    fun kotlinSerializationJsonEncoder(j: Json) = KotlinSerializationJsonEncoder(j)

    @Bean
    fun kotlinSerializationJsonDecoder(j: Json) = KotlinSerializationJsonDecoder(j)


}
