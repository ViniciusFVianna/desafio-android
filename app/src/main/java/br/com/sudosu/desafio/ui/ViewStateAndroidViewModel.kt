package br.com.sudosu.desafio.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.sudosu.desafio.utils.SingleLiveEvent

data class AlertMsg(
    val title: String,
    val msg: String
)

open class ViewStateAndroidViewModel<T>(initialViewState: T, application: Application) :
    AndroidViewModel(application) {

    protected val _viewState = MediatorLiveData<T>().apply { value = initialViewState }
    val viewState: LiveData<T>
        get() = _viewState

    protected val _alertMessage = SingleLiveEvent<AlertMsg>()
    val alertLiveData: LiveData<AlertMsg>
        get() = _alertMessage

    fun setAlertMessage(title: String, message: String) {
        _alertMessage.value = AlertMsg(title, message)
    }

}