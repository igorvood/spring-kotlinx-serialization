package com.example.demodelete.runner

import com.example.demodelete.controller.ProductController
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

//@Service
class WebRunner(
    private val productController : ProductController
):CommandLineRunner {
    override fun run(vararg args: String?) {
        val runBlocking = runBlocking {
            productController.findOne(1)
        }

        println(runBlocking)
    }
}