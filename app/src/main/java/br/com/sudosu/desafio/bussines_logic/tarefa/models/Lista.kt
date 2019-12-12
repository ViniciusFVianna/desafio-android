package br.com.sudosu.desafio.bussines_logic.tarefa.models

import com.google.gson.annotations.SerializedName


data class Lista(
    @SerializedName("lista")
    val id: List<String>
)