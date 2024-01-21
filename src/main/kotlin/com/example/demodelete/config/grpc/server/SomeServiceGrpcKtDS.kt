package com.example.demodelete.config.grpc.server


import com.example.demodelete.dto.ApiDto
import com.example.demodelete.dto.Errr
import com.example.demodelete.dto.QW
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.ResponseProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt
import kotlin.math.abs

@Service
class SomeServiceGrpcKtDS(
    val someServiceCoroutineClient: SomeServiceGrpcKt.SomeServiceCoroutineStub,
    val json: Json
    ) :
    SomeServiceGrpcKt.SomeServiceCoroutineImplBase() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override suspend fun execute(request: RequestProtoDto): ResponseProtoDto {
        log.info("execute: ${request.bar}")
        val encodeToString =
            json.encodeToString(ApiDto.serializer(), ApiDto(Errr("${request.bar} second"), QW(request.bar)))

        return ResponseProtoDto.newBuilder()
            .setStuff(encodeToString)
            .build()
    }

    override fun executeStream(request: RequestProtoDto): Flow<ResponseProtoDto> {
        log.info("executeStream: ${request.bar}")
        val cnt = abs(request.hashCode() % 10) + 2
        val range = (1..cnt)
        val f =// runBlocking {
            flow {
                range.map { num ->
            //        async {
                        if (num % 2 == 0) {

                                kotlinx.coroutines.delay(1000)
                                emit(
                                    someServiceCoroutineClient.execute(
                                        RequestProtoDto.newBuilder()
                                            .setBar("$num BAR executeStream")
                                            .build()
                                    )
                                )

                        } else {
                            emit(
                                someServiceCoroutineClient.execute(
                                    RequestProtoDto.newBuilder()
                                        .setBar("$num BAR executeStream")
                                        .build()
                                )
                            )

                        }
                  //  }//async

                //}
            }
        }
        return f
    }


    override suspend fun firstExecute(request: RequestProtoDto): ResponseProtoDto {
        log.info("firstExecute: ${request.bar}")
        delay(50)
        return ResponseProtoDto.newBuilder()
            .setStuff(request.bar + " secondExecute ")
            .build()
    }

    override suspend fun secondExecute(request: RequestProtoDto): ResponseProtoDto {
        log.info("secondExecute: ${request.bar}")
        return ResponseProtoDto.newBuilder()
            .setStuff(request.bar + " secondExecute ")
            .build()
    }

    override suspend fun firstExecuteThrow(request: RequestProtoDto): ResponseProtoDto {
        return someServiceCoroutineClient.secondExecuteThrow(request)
    }

    override suspend fun secondExecuteThrow(request: RequestProtoDto): ResponseProtoDto {
        throw CustomException(listOf("1", "2"))
    }

    class CustomException(val data: List<String>) : java.lang.RuntimeException(
        data.toString()
    )
}