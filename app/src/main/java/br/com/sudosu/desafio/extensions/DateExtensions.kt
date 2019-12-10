package br.com.sudosu.desafio.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.toMeasureDateString(): String {
    val format = SimpleDateFormat("dd/MM/yyyy  |  HH:mm", Locale("pt", "br"))
    return format.format(this)
}