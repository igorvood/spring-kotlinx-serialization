package com.example.demodelete.config.grpc.server

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.convert.DataSizeUnit
import org.springframework.boot.convert.DurationUnit
import org.springframework.util.unit.DataSize
import org.springframework.util.unit.DataUnit
import java.time.Duration
import java.time.temporal.ChronoUnit

//@ConfigurationProperties("grpc.server")
//@ConstructorBinding
data class GrpcServerProperties(
//    val ANY_IP_ADDRESS: String? = "*"
//    val ANY_IPv4_ADDRESS: String? = "0.0.0.0"
//    val ANY_IPv6_ADDRESS: String? = "::"
//    val address: String = "*",
    val port: Int = 9090,
//    @org.springframework.boot.convert.DurationUnit(java.time.temporal.ChronoUnit.SECONDS)
//    val shutdownGracePeriod: Duration? = null,
//    private const val enableKeepAlive = false,

    @DurationUnit(ChronoUnit.SECONDS)
    val keepAliveTime: Duration? = null,

    @DurationUnit(ChronoUnit.SECONDS)
    val keepAliveTimeout: Duration? = null,

    @DurationUnit(ChronoUnit.SECONDS)
    val permitKeepAliveTime: Duration? = null,

//    @DurationUnit(ChronoUnit.SECONDS)
//    private val permitKeepAliveWithoutCalls = false,

    @DataSizeUnit(DataUnit.BYTES)
    val maxInboundMessageSize: DataSize? = null,

    @DataSizeUnit(DataUnit.BYTES)
    val maxInboundMetadataSize: DataSize? = null,
//private const val healthServiceEnabled = false,
//private const val reflectionServiceEnabled = false,
//private val security: net.devh.boot.grpc.server.config.GrpcServerProperties.Security? = null,

)
