package br.com.sudosu.desafio.bussines_logic.tarefa.services

import br.com.sudosu.desafio.bussines_logic.tarefa.models.Lista
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Tarefa
import retrofit2.Call
import retrofit2.http.*


interface TarefaApi {
    @GET("/tarefa")
    fun tarefaGET(): Call<Lista>?

    @GET("/tarefa/{id}")
    fun tarefaIdGET(@Path("id")id: String): Call<Tarefa>
}