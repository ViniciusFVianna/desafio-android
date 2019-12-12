package br.com.sudosu.desafio.ui.scenes.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.ui.adapters.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        listHome.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getTarefa()?.observe(viewLifecycleOwner, Observer {
            listHome.adapter = HomeAdapter(it.data?.id!!, ::toTarefa)
        })
    }

    private fun toTarefa(id: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMainHomeFragment(id))
    }

}
