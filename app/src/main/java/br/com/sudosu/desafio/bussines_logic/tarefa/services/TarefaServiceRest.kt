package br.com.sudosu.desafio.bussines_logic.tarefa.services

import androidx.lifecycle.LiveData
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Lista
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Tarefa
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaApi
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaServiceInterface
import br.com.sudosu.desafio.services.RetrofitServices
import br.com.sudosu.desafio.services.executeCallFunction
import br.com.sudosu.desafio.services.models.ServiceResponse

class TarefaServiceRest :
    TarefaServiceInterface {

    private var tarefaApi: TarefaApi = RetrofitServices.getInstance().getService()

    override fun get(): LiveData<ServiceResponse<Lista>>? {
        return tarefaApi.tarefaGET()?.executeCallFunction()
    }

    override fun getById(id: String): LiveData<ServiceResponse<Tarefa>> {
        return tarefaApi.tarefaIdGET(id).executeCallFunction()
    }
}