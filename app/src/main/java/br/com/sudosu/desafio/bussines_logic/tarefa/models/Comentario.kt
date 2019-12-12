package br.com.sudosu.desafio.bussines_logic.tarefa.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comentario(
    val urlFoto: String? = null,
    val nome: String? = null,
    val nota: String? = null,
    val titulo: String? = null,
    val comentario: String? = null
): Parcelable