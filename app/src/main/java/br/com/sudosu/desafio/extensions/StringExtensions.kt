package br.com.sudosu.desafio.extensions

fun String.getNumbersOnly(): String = this.filter { it.isDigit() }