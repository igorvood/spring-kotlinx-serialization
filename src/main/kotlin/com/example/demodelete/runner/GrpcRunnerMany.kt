package com.example.demodelete.runner

import io.grpc.StatusException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

//@Service
class GrpcRunnerMany(
    val someServiceCoroutineStub: SomeServiceGrpcKt.SomeServiceCoroutineStub
) : CommandLineRunner {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun run(vararg args: String?) {
        val intRange = 1..500_000
        log.info("===================Single begin==========================")
        val runBlocking =  runBlocking {


            val map = intRange.map {
                async { kotlin.runCatching { someServiceCoroutineStub.firstExecute(
                    RequestProtoDto.newBuilder()
                        .setBar("firstExecute Bar!!!! $it")
                        .build()
                ) } }
            }

            val awaitAll = map.awaitAll()
            awaitAll
        }
        val isFailureList = runBlocking.filter { it.isFailure }.map { it.exceptionOrNull()!! }.groupBy { it }
            .map { it.key.javaClass to it.value.size }.toMap()
        val isSuccessList = runBlocking.filter { it.isSuccess }
        log.info("isFailureList -> ${isFailureList.size} isFailureList -> $isFailureList")
        log.info("isSuccessList -> ${isSuccessList.size}")

        log.info("durability -> ${isSuccessList.size.toDouble() / (isSuccessList.size + isFailureList.size).toDouble()}")
        log.info("===================Single end==========================")

    }
}