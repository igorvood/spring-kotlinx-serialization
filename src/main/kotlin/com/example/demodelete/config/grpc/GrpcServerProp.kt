package com.example.demodelete.config.grpc

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "grpc.server")
data class GrpcServerProp @ConstructorBinding constructor(
    val port: Int
)