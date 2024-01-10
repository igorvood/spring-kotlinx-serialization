package com.example.demodelete

import com.example.demodelete.config.SomeSystemConnectProp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SomeSystemConnectProp::class)
class DemoDeleteApplication

fun main(args: Array<String>) {
    runApplication<DemoDeleteApplication>(*args)
}
