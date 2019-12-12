package br.com.sudosu.desafio.ui.fragments

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.sudosu.desafio.extensions.safeNavigate
import java.util.*
import kotlin.collections.HashMap

abstract class ResultFragment: BaseFragment() {

    private val ARG_REQUEST_CODE = "r_code"
    private val ARG_REQUESTING_FRAGMENT = "r_frag"

    class SharedViewModel: ViewModel() {
        /**
         * Guarda os retornos de todos os fragmentos
         *
         * {
         *      UUIDFragAtual : {
         *          123 : <ResultCode, Bundle>,
         *          456 : <ResultCode, Bundle>
         *      }
         *      UIDFragAtual2 : {
         *          123 : <ResultCode, Bundle>,
         *          456 : <ResultCode, Bundle>
         *      }
         * }
         */
        val resultsLiveData = MutableLiveData<HashMap<String, HashMap<Int, Pair<Int, Bundle>>>>(
            HashMap()
        )
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _receivedRequestCode: Int? = null
    private var _requestingFragment: String? = null

    private val _fragmentUUID: String = UUID.randomUUID().toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _receivedRequestCode = arguments?.getInt(ARG_REQUEST_CODE)
        _requestingFragment = arguments?.getString(ARG_REQUESTING_FRAGMENT)
    }

    override fun onResume() {
        super.onResume()

        //Verifica se nao existe resultado no vm para qualquer request feita.
        sharedViewModel.resultsLiveData.observe(viewLifecycleOwner, Observer {
            it[_fragmentUUID]?.entries?.forEach { requestCodeResult ->
                onFragmentResult(requestCode = requestCodeResult.key, resultCode = requestCodeResult.value.first, data = requestCodeResult.value.second)
            }
            //Limpa depois de processar todos
            it.remove(_fragmentUUID)
        })
    }

    /**
     * Funcoes chamadas para criar um fragmento que se espera resultados.
     */

    fun navigateForResults(destinationId: Int, requestCode: Int) {
        navigateForResults(destinationId, requestCode, Bundle())
    }

    fun navigateForResults(navDirections: NavDirections, requestCode: Int) {
        navigateForResults(navDirections.actionId, requestCode, navDirections.arguments)
    }

    fun navigateForResults(destinationId:Int, requestCode: Int, arguments: Bundle) {
        arguments.putInt(ARG_REQUEST_CODE, requestCode)
        arguments.putString(ARG_REQUESTING_FRAGMENT, _fragmentUUID)
        findNavController().safeNavigate(destinationId, arguments, null)
    }

    /**
     * Chamada pelo fragmento antes de se encerrar, colocando um resultado.
     */
    fun setResult(resultCode: Int, data: Bundle) {
        val allResults = sharedViewModel.resultsLiveData.value!!
        val reqRes = allResults.getOrPut(_requestingFragment ?: return) { HashMap() }
        reqRes[_receivedRequestCode!!] = Pair(resultCode, data)
        allResults[_requestingFragment!!] = reqRes
        sharedViewModel.resultsLiveData.value = allResults
    }

    open fun onFragmentResult(requestCode:Int, resultCode: Int, data: Bundle) { }

    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.resultsLiveData.value?.remove(_fragmentUUID)
    }
}