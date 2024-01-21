package com.example.demodelete.runner

import com.example.demodelete.controller.ProductController
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class WebRunnerMany(
    private val productController : ProductController
):CommandLineRunner {

    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun run(vararg args: String?) {
        val intRange = 1..100_000
        log.info("start ->")
        val runBlocking = runBlocking {

            val map = intRange.map {
                async { kotlin.runCatching { productController.findOne(it)} }
            }

            val awaitAll = map.awaitAll()
            awaitAll


        }
        val isFailureList = runBlocking.filter { it.isFailure }
        val isSuccessList = runBlocking.filter { it.isSuccess }
        log.info("isFailureList -> ${isFailureList.size}")
        log.info("isSuccessList -> ${isSuccessList.size}")

        log.info("durability -> ${isSuccessList.size.toDouble()/(isSuccessList.size +isFailureList.size).toDouble()}")

    }
}