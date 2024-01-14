package com.example.demodelete.config.grpc.server

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.ResponseProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Service
class SomeServiceGrpcKtDS : SomeServiceGrpcKt.SomeServiceCoroutineImplBase() {

    override suspend fun execute(request: RequestProtoDto): ResponseProtoDto {
        return super.execute(request)
    }

    override fun executeStream(request: RequestProtoDto): Flow<ResponseProtoDto> {
        return super.executeStream(request)
    }
}