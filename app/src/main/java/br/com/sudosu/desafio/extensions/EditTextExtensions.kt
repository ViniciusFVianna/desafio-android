package br.com.sudosu.desafio.extensions

import android.widget.EditText

fun EditText.onFocusLost(block: (String) -> Unit) {
    this.setOnFocusChangeListener { _, b -> if (!b) block(this.text.toString()) }
}