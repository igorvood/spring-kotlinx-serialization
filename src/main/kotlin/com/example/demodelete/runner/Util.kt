package com.example.demodelete.runner

import com.example.demodelete.dto.ApiDto
import org.slf4j.Logger
import java.time.Instant
import java.time.LocalDateTime

fun <T>extracted(runBlocking: List<Result<T>>, log: Logger, begin: Instant) {
    val filter = runBlocking.filter { it.isFailure }
    val isFailureList = filter.map { it.exceptionOrNull()!! }.groupBy { it }
        .map { it.key.javaClass to it.value.size }.toMap()
    val isSuccessList = runBlocking.filter { it.isSuccess }
    log.info("isFailureList -> ${filter.size} isFailureList -> $isFailureList")
    log.info("isSuccessList -> ${isSuccessList.size}")

    log.info("durability -> ${isSuccessList.size.toDouble() / (isSuccessList.size + filter.size).toDouble()}")
    val now = Instant.now()
    log.info("duration -> ${(now.toEpochMilli() - begin.toEpochMilli()).toDouble() / 1000.toDouble()} sec")







}