package com.example.demodelete.client

import com.example.demodelete.dto.ApiDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class SomeClient(
    private val webClient: WebClient
) {

    suspend fun someRequest(id: String): ApiDto {

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
            .awaitBody<ApiDto>()
        return awaitBody
    }
}