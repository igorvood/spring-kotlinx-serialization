package com.example.demodelete.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "somesystem.client")
data class SomeSystemConnectProp(
    override val host: String,

    override val port: String?,

    override val timeout: Int,

    override val http: HttpEnum,
) : WebClientProp
