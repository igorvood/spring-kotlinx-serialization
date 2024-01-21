package com.example.demodelete.runner

import io.grpc.StatusException
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
        log.info("===================Single begin==========================")
        runBlocking {
            val firstExecute = someServiceCoroutineStub.firstExecute(
                RequestProtoDto.newBuilder()
                    .setBar("firstExecute Bar!!!!")
                    .build()
            )
            log.info(firstExecute.stuff)
        }
        log.info("===================Single end==========================")

    }
}