package com.example.demodelete.config.grpc

import io.grpc.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Configuration
class GrpcConfig {

    @Bean
    fun grpcServer(someServiceCoroutineImplBase: SomeServiceGrpcKt.SomeServiceCoroutineImplBase): Server {

        return io.grpc.ServerBuilder
            .forPort(5051)
            .addService(someServiceCoroutineImplBase)
            .build()
            .start()

    }
}