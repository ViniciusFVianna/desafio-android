package br.com.sudosu.desafio.ui.scenes.servicos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sudosu.desafio.R

/**
 * A simple [Fragment] subclass.
 */
class ServicoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servico, container, false)
    }


}
