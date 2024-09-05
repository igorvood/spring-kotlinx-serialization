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

@Service
class GrpcRunner(
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
        log.info("===================Single throw begin==========================")
        runBlocking {
            try {
                val firstExecute = someServiceCoroutineStub.firstExecuteThrow(
                    RequestProtoDto.newBuilder()
                        .setBar("firstExecuteThrow Bar!!!!")
                        .build()
                )
                log.info(firstExecute.stuff)
            } catch (e: StatusException) {
                log.info(
                    """class:${e::class.simpleName}
                  |message:${e.message}
                  |trailers:${e.trailers}
                  ||fillInStackTrace:${e.fillInStackTrace()}
                  |""".trimMargin()
                )
            }

        }
        log.info("===================Single throw end==========================")
        log.info("===================Flow begin==========================")
        runBlocking {
            val request = RequestProtoDto.newBuilder()
                .setBar("executeStream Bar!!!!")
                .build()
            val firstExecute = someServiceCoroutineStub.executeStream(
                request
            )
            log.info("before stream")
            firstExecute.map { f ->
                log.info(f.stuff)
            }
                .collect()
            log.info("after collect")

        }
        log.info("===================Flow end==========================")

    }
}