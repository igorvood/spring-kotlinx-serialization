package com.example.demodelete.config.grpc.server

import com.example.demodelete.config.grpc.GrpcServerProp
import io.grpc.Server
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Configuration
@EnableConfigurationProperties(GrpcServerProp::class)
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