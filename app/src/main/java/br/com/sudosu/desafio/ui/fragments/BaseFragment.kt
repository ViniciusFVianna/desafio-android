package br.com.sudosu.desafio.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.ui.MainActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    enum class StatusBarTintStyle { LIGHT, DARK }

    open var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT

    open var hideToolbar = false

    open var hasBottomBar = true

    open var displayUpButton = true

    abstract var title: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar?.isVisible = !hideToolbar

        updateTitle(title)
        updateStatusBarTint(statusBarTintStyle)

        changeHasBottomBar(hasBottomBar)

        toolbar?.also {
            (requireActivity() as AppCompatActivity).also {
                it.setSupportActionBar(toolbar)
                it.supportActionBar?.setDisplayShowTitleEnabled(false)
                it.supportActionBar?.setDisplayHomeAsUpEnabled(displayUpButton)
                it.supportActionBar?.setDisplayShowHomeEnabled(displayUpButton)
                it.supportActionBar?.setHomeButtonEnabled(displayUpButton)
                it.supportActionBar?.setDisplayUseLogoEnabled(displayUpButton)
                it.supportActionBar?.setLogo(R.drawable.icon_tool_logo)
                it.supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_back)
            }
        }
    }

    fun changeHasBottomBar(hasBottomBar: Boolean) {
        (requireActivity() as? BaseActivity)?.setBottomBarVisibility(hasBottomBar)
        (requireActivity() as? MainActivity)?.isShowingBottomBar = hasBottomBar
    }

    fun updateTitle(title: String) {
        this.title = title
        toolbarTitle?.text = title
    }

    private fun updateStatusBarTint(statusBarTintStyle: StatusBarTintStyle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            var flags = activity?.window?.decorView?.systemUiVisibility ?: return

            flags = if (statusBarTintStyle == StatusBarTintStyle.DARK) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //Ver aqui como inverter a flag
            }

            activity?.window?.decorView?.systemUiVisibility = flags
        }
    }

}

interface BaseActivity {
    fun setToolbarVisibility(isVisible: Boolean)
    fun setToolbarTitle(title: String)
    fun setBottomBarVisibility(isVisible: Boolean)
}