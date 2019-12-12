package br.com.sudosu.desafio.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import br.com.sudosu.desafio.utils.SingleLiveEvent

open class ViewStateViewModel<T>(initializeViewState: T) : ViewModel() {

    protected val _viewState = MediatorLiveData<T>().apply { value = initializeViewState }
    val viewState: LiveData<T>
    get() = _viewState

    protected val _alertMessage = SingleLiveEvent<AlertMsg>()
    val alertMutableLiveData: LiveData<AlertMsg>
        get() = _alertMessage

    fun setAlertMessage(title: String, message: String) {
        _alertMessage.value = AlertMsg(title, message)
    }
}