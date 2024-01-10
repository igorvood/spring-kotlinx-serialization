package com.example.demodelete.controller

import com.example.demodelete.client.SomeClient
import com.example.demodelete.dto.ApiDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val someClient: SomeClient
) {
    @GetMapping("/{id}")
    suspend fun findOne(@PathVariable id: Int): ApiDto {
        return someClient.someRequest(id.toString())
    }

    @GetMapping("second/{id}")
    suspend fun second(@PathVariable id: Int): ApiDto {
        return ApiDto("$id second")
    }

}