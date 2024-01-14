package com.example.demodelete.config.grpc.client

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Configuration
class GrpcClientConfig {

    @Bean
    fun grpcManagedChannel(): ManagedChannel {

       return ManagedChannelBuilder
           .forAddress("localhost", 15001)
           .usePlaintext()
           .build()
    }

    @Bean
    fun grpcManagasdedChannel(
        managedChannel: ManagedChannel
    ): SomeServiceGrpcKt.SomeServiceCoroutineStub {
        return SomeServiceGrpcKt.SomeServiceCoroutineStub(managedChannel)
    }
}