package com.example.demodelete.config

interface WebClientProp {

    val host: String
    val port: String?
    val timeout: Int
    val http: HttpEnum


    fun baseUrl() = "${http.s}${host}" + (if (port!=null && port!!.isNotEmpty())":$port" else "") + "/"
}