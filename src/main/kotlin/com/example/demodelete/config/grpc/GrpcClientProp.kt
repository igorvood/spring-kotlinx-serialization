package com.example.demodelete.config.grpc

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "grpc.client")
data class GrpcClientProp @ConstructorBinding constructor(
    val host: String,
    val port: Int
)