package br.com.sudosu.desafio.bussines_logic.tarefa.models

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.google.gson.annotations.SerializedName


data class Lista(
    @SerializedName("lista")
    val id: List<String>
){
    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Lista>(){
        override fun areContentsTheSame(oldItem: Lista, newItem: Lista): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Lista, newItem: Lista): Boolean {
            return oldItem.id == newItem.id
        }
    }
}