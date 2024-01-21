package com.example.demodelete.client

import com.example.demodelete.dto.ApiDto
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class SomeClient(
    private val webClient: WebClient,
    private val j: Json
) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    suspend fun someRequest(id: String): ApiDto {

        val serializer = ApiDto.serializer()
//        j.encodeToString(ApiDto. ApiDto("asdsad"))

        val retrieve = webClient
            .get()
            .uri { ub ->
                ub
                    .path("second/$id")
//                    .query("$id")
                    .build()
            }
//                .header()
            .retrieve()


        val awaitBody = retrieve
//            .awaitBody<String>()
            .awaitBody<ApiDto>()
//        log.info(awaitBody.toString())

//        val decodeFromString = j.decodeFromString(ApiDto.serializer(), awaitBody)


        return awaitBody
    }
}