package com.example.demodelete.config.grpc.client

import com.example.demodelete.config.grpc.GrpcClientProp
import com.example.demodelete.config.grpc.GrpcServerProp
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.vood.grpc.example.v1.SomeServiceGrpcKt

@Configuration
@EnableConfigurationProperties(GrpcClientProp::class)
class GrpcClientConfig(
    private val grpcClientProp: GrpcClientProp
) {

    @Bean
    fun grpcManagedChannel(): ManagedChannel {

       return ManagedChannelBuilder
           .forAddress(grpcClientProp.host, grpcClientProp.port)
           .usePlaintext()
           .build()
    }

    @Bean
    fun someServiceCoroutineStub(
        managedChannel: ManagedChannel
    ): SomeServiceGrpcKt.SomeServiceCoroutineStub {
        return SomeServiceGrpcKt.SomeServiceCoroutineStub(managedChannel)
    }
}