package com.example.demodelete.config.grpc

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "grpc.client")
data class GrpcClientProp (
    val host: String,
    val port: Int
)