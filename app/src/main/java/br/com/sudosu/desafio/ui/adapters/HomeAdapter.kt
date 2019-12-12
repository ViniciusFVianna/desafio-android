package br.com.sudosu.desafio.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Lista
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
    private val listas: List<String>,
    val onClick: (String) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    override fun getItemCount(): Int = listas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        // Retorna o Viewholder que contem todas as views
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val view = holder.itemView
        val lista = listas[position]

        with(view){
            itemText.text = lista

            setOnClickListener { onClick(lista) }
        }
    }
    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view)
}