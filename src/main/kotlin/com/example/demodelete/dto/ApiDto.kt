package com.example.demodelete.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiDto(
    val data: IData,
    val inlineData: QW
){
    @SerialName("POP")
    val const1 = "const"
}

@JvmInline
@Serializable
value class QW(val value: String)

@Serializable
sealed interface IData

@Serializable
data class DataOk(val s: String):IData

@Serializable
data class Errr(val s: String):IData