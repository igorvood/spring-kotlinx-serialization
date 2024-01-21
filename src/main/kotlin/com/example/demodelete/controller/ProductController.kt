package com.example.demodelete.controller

import com.example.demodelete.client.SomeClient
import com.example.demodelete.dto.ApiDto
import com.example.demodelete.dto.Errr
import com.example.demodelete.dto.QW
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val someClient: SomeClient,
    private val serializer: Json
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Int): ApiDto {
        log.info("firstExecute: ${id}")
        delay(50)
//        return someClient.someRequest(id.toString())
        return ApiDto(Errr("$id second"), QW(id.toString()))
    }

    @GetMapping("second/{id}")
    @ResponseBody
    suspend fun second(@PathVariable id: Int): ApiDto {
//        val apiDto = ApiDto(DataOk("$id second"))
        val apiDto = ApiDto(Errr("$id second"), QW(id.toString()))

        log.info("second -> " + serializer.encodeToString(ApiDto.serializer(), apiDto))
//        return j.encodeToString(ApiDto.serializer(), apiDto)
        return apiDto
    }

}