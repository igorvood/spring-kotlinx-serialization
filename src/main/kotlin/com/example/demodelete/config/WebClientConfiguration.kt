package com.example.demodelete.config

import io.netty.channel.ChannelOption
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfiguration {


    @Bean
    fun someSystem(d: WebClientProp) = createWebClient(d)

    private fun createWebClient(webProp: WebClientProp): WebClient{
        return WebClient.builder()
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .baseUrl(webProp.baseUrl())
            .clientConnector(
                ReactorClientHttpConnector(HttpClient.newConnection()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webProp.timeout)
                    .responseTimeout(Duration.ofMillis(webProp.timeout.toLong()))
                    .compress(true)
                )
            )
            .build()
    }
}