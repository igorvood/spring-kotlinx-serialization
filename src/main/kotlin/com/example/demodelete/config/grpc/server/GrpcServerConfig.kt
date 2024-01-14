package com.example.demodelete.config.grpc.server

import io.grpc.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Configuration
class GrpcServerConfig {

    @Bean
    fun grpcServer(someServiceCoroutineImplBase: SomeServiceGrpcKt.SomeServiceCoroutineImplBase): Server {

        return io.grpc.ServerBuilder
            .forPort(15001)
            .maxInboundMetadataSize(1000000)
            .addService(someServiceCoroutineImplBase)
            .build()
            .start()

    }
}