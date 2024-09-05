package com.example.demodelete.runner

import com.example.demodelete.controller.ProductController
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime

@Service
class WebRunnerMany(
    private val productController: ProductController
) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun run(vararg args: String?) {
        val intRange = 1..10//1_000
        log.info("start ->")
        val begin = Instant.now()
        val runBlocking = runBlocking {

            val map = intRange.map {
                async { kotlin.runCatching { productController.findOne(it) } }
            }

            val awaitAll = map.awaitAll()
            awaitAll


        }
        extracted(runBlocking, log, begin)

    }


}