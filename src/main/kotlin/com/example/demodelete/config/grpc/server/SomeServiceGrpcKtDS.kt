package com.example.demodelete.config.grpc.server


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.ResponseProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt
import kotlin.math.abs

@Service
class SomeServiceGrpcKtDS(val someServiceCoroutineClient: SomeServiceGrpcKt.SomeServiceCoroutineStub) : SomeServiceGrpcKt.SomeServiceCoroutineImplBase() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override suspend fun execute(request: RequestProtoDto): ResponseProtoDto {
        log.info("execute: ${request.bar}")
        return ResponseProtoDto.newBuilder()
            .setStuff(request.bar+"-> BAR execute")
            .build()
    }

    override fun executeStream(request: RequestProtoDto): Flow<ResponseProtoDto> {
        log.info("executeStream: ${request.bar}")
        val cnt = abs(request.hashCode() % 10) + 2
        val map = (1..cnt)
            .asFlow()
            .map { cnt ->

                someServiceCoroutineClient.execute(
                    RequestProtoDto.newBuilder()
                        .setBar("$cnt BAR executeStream")
                        .build()
                )
            }

        return map
    }


    override suspend fun firstExecute(request: RequestProtoDto): ResponseProtoDto {
        log.info("firstExecute: ${request.bar}")
        return someServiceCoroutineClient.secondExecute(request)
    }

    override suspend fun secondExecute(request: RequestProtoDto): ResponseProtoDto {
        log.info("secondExecute: ${request.bar}")
        return ResponseProtoDto.newBuilder()
            .setStuff(request.bar+" secondExecute ")
            .build()
    }

    override suspend fun firstExecuteThrow(request: RequestProtoDto): ResponseProtoDto {
        return someServiceCoroutineClient.secondExecuteThrow(request)
    }

    override suspend fun secondExecuteThrow(request: RequestProtoDto): ResponseProtoDto {
        throw CustomException(listOf("1", "2"))
    }

    class CustomException(val data: List<String>): java.lang.RuntimeException(
        data.toString()
    )
}