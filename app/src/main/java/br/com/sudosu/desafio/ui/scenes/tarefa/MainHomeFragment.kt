package br.com.sudosu.desafio.ui.scenes.tarefa


import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.sudosu.desafio.CALL_PRMISSIONS
import br.com.sudosu.desafio.MAP_VIEW_BUNDLE_KEY
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.ui.adapters.ComentarioAdapter
import br.com.sudosu.desafio.ui.fragments.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import jp.wasabeef.glide.transformations.MaskTransformation
import kotlinx.android.synthetic.main.fragment_main_home.*
import pub.devrel.easypermissions.EasyPermissions
/**
 * A simple [Fragment] subclass.
 */
class MainHomeFragment : BaseFragment(){

    override var title: String = ""
    override var hideToolbar: Boolean = false
    override var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT
    override var displayUpButton: Boolean = true

    private lateinit var markerOptions: MarkerOptions
    lateinit var marker: Marker


    private lateinit var viewModel: TarefaViewModel
    private val args by navArgs<MainHomeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TarefaViewModel::class.java)

        viewModel.setId(args.tarefaId)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
        recyclerComents.layoutManager = LinearLayoutManager(requireContext())

        // LiveData com retorno do servidor para o povoamento da tela
        viewModel.getTarefa()?.observe(viewLifecycleOwner, Observer {
            val tarefa = it.data
            updateTitle("${tarefa?.cidade} - ${tarefa?.bairro}")

            // Glide para o carregamento do das imagens
            Glide.with(tarefaImg.context)
                .load(tarefa?.urlFoto)
                .error(R.drawable.image_home)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .transform(CenterCrop(), MaskTransformation(R.drawable.image_home))
                .into(tarefaImg)

            Glide.with(tarefaLogo.context)
                .load(tarefa?.urlLogo)
                .error(R.drawable.image_background)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .transform(CircleCrop(), MaskTransformation(R.drawable.image_background))
                .into(tarefaLogo)

            tarefaText.text = tarefa?.titulo
            tarefaDesc.text = tarefa?.texto
            tarefaTextEnder.text = tarefa?.endereco

            with(tarefaMap) {
                onCreate(mapViewBundle)
                getMapAsync { map ->
                    MapsInitializer.initialize(requireContext())
                    setMapLocation(map, tarefa?.latitude!!, tarefa.longitude!!)
                }

                recyclerComents.adapter = ComentarioAdapter(tarefa?.comentarios ?: emptyList())
            }

            tarefaLigar.setOnClickListener {
                if (viewModel.checkForPermission()) {
                    EasyPermissions.hasPermissions(requireContext(), *CALL_PRMISSIONS)
                    val fone = tarefa?.telefone.toString()
                    val uri = Uri.parse("tel:$fone")
                    val intent = Intent(Intent.ACTION_DIAL, uri)
                    startActivity(intent)
                } else {
                    val fone = tarefa?.telefone.toString()
                    val uri = Uri.parse("tel:$fone")
                    val intent = Intent(Intent.ACTION_DIAL, uri)
                    startActivity(intent)
                }
            }

            tarefaEndereco.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.alert_title, "Endereço"))
                    .setMessage(getString(R.string.alert_message, tarefa?.endereco))
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }

            viewModel.setLatitude(tarefa?.latitude)
            viewModel.setLongetude(tarefa?.longitude)

        })
        tarefaComents.setOnClickListener {
            scroll.post {
                scroll.fullScroll(View.FOCUS_DOWN)
            }
        }

        tarefaServico.setOnClickListener {
            findNavController().navigate(R.id.action_mainHomeFragment_to_servicoFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.actionSearch -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onBackPressed(): Boolean{
         requireActivity().onBackPressed()
       return true
    }

    private fun setMapLocation(map: GoogleMap,latitude: Double, longitude: Double) {
        with(map) {

            val location = LatLng(latitude, longitude)

            moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))

            mapType = GoogleMap.MAP_TYPE_NORMAL

                markerOptions = MarkerOptions().position(location)

                markerOptions.position(location)
                marker = addMarker(markerOptions)

                val uiSettings = uiSettings

                uiSettings.isZoomControlsEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
                uiSettings.isIndoorLevelPickerEnabled = true
                uiSettings.isCompassEnabled = true
                uiSettings.isRotateGesturesEnabled = false
                uiSettings.isScrollGesturesEnabled = false
                uiSettings.isTiltGesturesEnabled = false
                uiSettings.isZoomGesturesEnabled = false

        }
    }

}
