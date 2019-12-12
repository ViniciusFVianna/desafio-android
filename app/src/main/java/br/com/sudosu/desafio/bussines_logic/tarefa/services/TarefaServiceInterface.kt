package br.com.sudosu.desafio.bussines_logic.tarefa.services

import androidx.lifecycle.LiveData
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Lista
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Tarefa
import br.com.sudosu.desafio.services.models.ServiceResponse

interface TarefaServiceInterface {

    fun get(): LiveData<ServiceResponse<Lista>>?

    fun getById(id: String): LiveData<ServiceResponse<Tarefa>>
}