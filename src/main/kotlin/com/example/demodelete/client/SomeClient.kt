package com.example.demodelete.client

import com.example.demodelete.dto.ApiDto
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class SomeClient(
    private val webClient: WebClient,
    private val j: Json
) {

    suspend fun someRequest(id: String): ApiDto {

        val serializer = ApiDto.serializer()
//        j.encodeToString(ApiDto. ApiDto("asdsad"))

        val awaitBody = webClient
            .get()
            .uri { ub ->
                ub
                    .path("second/$id")
//                    .query("$id")
                    .build()
            }
//                .header()
            .retrieve()
//            .bodyToMono<ApiDto>(ApiDto::class.java)
            .awaitBody<ApiDto>()
//        val decodeFromString = j.decodeFromString(ApiDto.serializer(), awaitBody)

//        TODO()
        return awaitBody
    }
}