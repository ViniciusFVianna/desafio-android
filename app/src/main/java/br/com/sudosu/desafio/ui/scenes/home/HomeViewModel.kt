package br.com.sudosu.desafio.ui.scenes.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Lista
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaServiceInterface
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaServiceRest
import br.com.sudosu.desafio.services.models.ServiceResponse

class HomeViewModel(
    private val homeService: TarefaServiceInterface = TarefaServiceRest()
): ViewModel() {

   fun getTarefa(): LiveData<ServiceResponse<Lista>>?{
       return homeService.get()
   }
}