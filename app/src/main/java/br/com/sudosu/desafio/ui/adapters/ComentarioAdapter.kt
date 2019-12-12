package br.com.sudosu.desafio.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.bussines_logic.tarefa.models.Comentario
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import jp.wasabeef.glide.transformations.MaskTransformation
import kotlinx.android.synthetic.main.cell_coments.view.*

class ComentarioAdapter(
    private val comentarios: List<Comentario>
): RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder>(){

    override fun getItemCount(): Int = comentarios.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_coments, parent, false)
        // Retorna o Viewholder que contem todas as views
        return ComentarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        val view = holder.itemView
        val comentario = comentarios[position]

        with(view){
            Glide.with(itemFoto.context)
                .load(comentario.urlFoto)
                .error(R.drawable.image_background)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .transform(CircleCrop(), MaskTransformation(R.drawable.image_background))
                .into(itemFoto)
            itemName.text = comentario.nome
            itemComent.text = comentario.comentario
            itemTitulo.text = comentario.titulo
            itemNota.rating = comentario.nota!!.toFloat()
        }
    }
    // ViewHolder fica vazio pois usamos o import do Android Kotlin Extensions
    class ComentarioViewHolder(view: View) : RecyclerView.ViewHolder(view)
}