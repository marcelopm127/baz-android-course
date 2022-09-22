package com.example.projectwizeline.domain.constant

interface Constants {
    companion object {
        const val BASE_URL = "https://api.bitso.com/v3/"


        //errors
        const val ERROR_UNKNOWN = "Error inesperado"
        const val ERROR_INTERNET_CONNECTION = "Hubo un problema de conexión a internet"
        const val ERROR_RESOURCE_SOURCE = "Error con el origen del recurso, problemas de conexión a internet"
    }
}