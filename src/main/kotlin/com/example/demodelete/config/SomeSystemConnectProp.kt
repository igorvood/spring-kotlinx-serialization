package com.example.demodelete.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "somesystem.client")
data class SomeSystemConnectProp @ConstructorBinding constructor(
    override val host: String,

    override val port: String?,

    override val timeout: Int,

    override val http: HttpEnum,
) : WebClientProp
