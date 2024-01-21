package com.example.demodelete.runner

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt
import java.time.Instant
import java.time.LocalDateTime

//@Service
class GrpcRunnerMany(
    val someServiceCoroutineStub: SomeServiceGrpcKt.SomeServiceCoroutineStub
) : CommandLineRunner {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun run(vararg args: String?) {
        val intRange = 1..500_000
        val begin = Instant.now()
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
        extracted(runBlocking, log, begin)
        log.info("===================Single end==========================")

    }
}