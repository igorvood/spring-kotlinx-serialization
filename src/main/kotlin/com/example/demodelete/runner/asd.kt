package com.example.demodelete.runner

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.ResponceProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpc
import ru.vood.grpc.example.v1.SomeServiceGrpcKt
import java.util.concurrent.Executors.newFixedThreadPool

class asd {

    fun asdsa() {

//        SomeServiceGrpcKt.SomeServiceCoroutineStub()
    }
}

class asdasd: SomeServiceGrpcKt.SomeServiceCoroutineImplBase(  coroutineContext = newFixedThreadPool(4).asCoroutineDispatcher()){

    override suspend fun execute(request: RequestProtoDto): ResponceProtoDto {
        return super.execute(request)
    }

    override fun executeStream(request: RequestProtoDto): Flow<ResponceProtoDto> {
        return super.executeStream(request)
    }
}

