package com.example.logincalculadoracompose

fun suma(factor1: String?, factor2: String?): String {
    val numero1 = factor1?.toDoubleOrNull() ?: 0.0
    val numero2 = factor2?.toDoubleOrNull() ?: 0.0

    val resultado = numero1 + numero2

    return resultado.toString()
}

fun resta(factor1: String?, factor2: String?): String {
    val numero1 = factor1?.toDoubleOrNull() ?: 0.0
    val numero2 = factor2?.toDoubleOrNull() ?: 0.0

    val resultado = numero1 - numero2

    return resultado.toString()
}

fun multiplica(factor1: String?, factor2: String?): String {
    val numero1 = factor1?.toDoubleOrNull() ?: 0.0
    val numero2 = factor2?.toDoubleOrNull() ?: 0.0

    val resultado = numero1 * numero2

    return resultado.toString()
}

fun divide(factor1: String?, factor2: String?): String {
    val numero1 = factor1?.toDoubleOrNull() ?: 0.0
    val numero2 = factor2?.toDoubleOrNull() ?: 0.0

    val resultado = numero1 / numero2

    return resultado.toString()
}