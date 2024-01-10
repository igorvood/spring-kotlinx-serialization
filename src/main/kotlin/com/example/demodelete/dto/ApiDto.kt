package com.example.demodelete.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiDto(
    val data: IData
){
    val const1 = "const"
}

@Serializable
sealed interface IData

@Serializable
data class DataOk(val s: String):IData

@Serializable
data class Errr(val s: String):IData