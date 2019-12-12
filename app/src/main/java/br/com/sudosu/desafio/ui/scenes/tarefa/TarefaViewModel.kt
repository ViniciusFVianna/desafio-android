package br.com.sudosu.desafio.ui.scenes.tarefa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.sudosu.desafio.CALL_PRMISSIONS
import br.com.sudosu.desafio.DesafioApplication
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Tarefa
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaServiceInterface
import br.com.sudosu.desafio.bussines_logic.tarefa.services.TarefaServiceRest
import br.com.sudosu.desafio.extensions.setValueAndNotify
import br.com.sudosu.desafio.services.models.ServiceResponse
import pub.devrel.easypermissions.EasyPermissions

class TarefaViewModel(
    private val tarefaService: TarefaServiceInterface = TarefaServiceRest()
) : ViewModel() {

    data class TarefaViewState(
        var id: String? = null,
        var cidade: String? = null,
        var bairro: String? = null,
        var urlFoto: String? = null,
        var urlLogo: String? = null,
        var titulo: String? = null,
        var telefone: String? = null,
        var texto: String? = null,
        var endereco: String? = null,
        var latitude: Double? = null,
        var longitude: Double? = null,
        var comentarios: List<ComentarioViewState>? = emptyList()
    )

    data class ComentarioViewState(
        var urlFoto: String? = null,
        var nome: String? = null,
        var nota: String? = null,
        var titulo: String? = null,
        var comentario: String? = null
    )

    private val _tarefaViewState: MutableLiveData<TarefaViewState> =
        MutableLiveData(TarefaViewState())
    val tarefaViewState: LiveData<TarefaViewState>
        get() = _tarefaViewState

    private val _comentarioViewState: MutableLiveData<ComentarioViewState> =
        MutableLiveData(ComentarioViewState())
    val comentarioViewState: LiveData<TarefaViewState>
        get() = _tarefaViewState

    private val _grantedLocationPermission = MutableLiveData<Boolean>()
    val grantedLocationPermission: LiveData<Boolean>
        get() = _grantedLocationPermission

    init {
        checkForPermission()
    }

    fun setId(id: String) {
        _tarefaViewState.value?.id = id
    }

    fun setLatitude(latitude: Double?){
        _tarefaViewState.value?.latitude = latitude
    }
    fun setLongetude(longitude: Double?){
        _tarefaViewState.value?.longitude = longitude
    }

     fun getTarefa(): LiveData<ServiceResponse<Tarefa>>? {
        return tarefaService.getById(_tarefaViewState.value?.id!!)
    }

    fun checkForPermission(): Boolean {
        val hasPermission = EasyPermissions.hasPermissions(DesafioApplication.getInstance(), *CALL_PRMISSIONS)
        _grantedLocationPermission.value = hasPermission
        return hasPermission
    }


}