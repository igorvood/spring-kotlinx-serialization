package com.example.demodelete.runner

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Service
class GrpcRunner(val someServiceCoroutineStub: SomeServiceGrpcKt.SomeServiceCoroutineStub) : CommandLineRunner {
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
        log.info("===================Flow begin==========================")
        runBlocking {
            val firstExecute = someServiceCoroutineStub.executeStream(
                RequestProtoDto.newBuilder()
                    .setBar("executeStream Bar!!!!")
                    .build()
            )
            firstExecute.map {f ->
                log.info(f.stuff)
            }

        }
        log.info("===================Flow end==========================")

    }
}