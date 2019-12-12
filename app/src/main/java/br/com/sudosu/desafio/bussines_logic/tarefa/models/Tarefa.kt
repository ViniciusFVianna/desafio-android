package br.com.sudosu.desafio.bussines_logic.tarefa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tarefa(
   val id: String? = null,
val cidade: String? = null,
val bairro: String? = null,
val urlFoto: String? = null,
val urlLogo: String? = null,
val titulo: String? = null,
val telefone: String? = null,
val texto: String? = null,
val endereco: String? = null,
val latitude: Double? = null,
val longitude: Double? = null,
val comentarios : List<Comentario>?
):Parcelable