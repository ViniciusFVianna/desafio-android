package br.com.sudosu.desafio.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.safeNavigate(@IdRes resId: Int) {
    try {
        this.navigate(resId)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle?) {
    try {
        this.navigate(resId, args)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun NavController.safeNavigate(@IdRes resId: Int, args: Bundle?, navOptions: NavOptions?) {
    try {
        this.navigate(resId, args, navOptions)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        this.navigate(navDirections)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

