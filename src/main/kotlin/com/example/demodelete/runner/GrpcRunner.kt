package com.example.demodelete.runner

import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import ru.vood.grpc.example.v1.RequestProtoDto
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Service
class GrpcRunner(val someServiceCoroutineStub: SomeServiceGrpcKt.SomeServiceCoroutineStub) : CommandLineRunner {
    override fun run(vararg args: String?) {
        runBlocking {
            val firstExecute = someServiceCoroutineStub.firstExecute(
                RequestProtoDto.newBuilder()
                    .setBar("Bar!!!!")
                    .build()
            )


            println(firstExecute.stuff)

        }

    }
}