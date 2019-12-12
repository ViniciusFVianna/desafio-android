package br.com.sudosu.desafio.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.isVisible
import br.com.sudosu.desafio.R

class DesafioBottomNavigationView : FrameLayout{
    lateinit var menu: Menu
//    var selectedItemId = R.id.mainFragment
    private var onNavItemSelectedListener: ((item: MenuItem) -> Boolean)? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, intDefStyle: Int)
            : super(context, attrs, intDefStyle) {
        init()
    }


    private fun init() {

//        inflate(context, R.layout.bottom_bar_layout, this)

        inflateMenu()

//        tab0.setOnClickListener {
//            selectItem(R.id.mainFragment)
//        }
//
//        tab1.setOnClickListener {
//            selectItem(R.id.historyFragment)
//        }
//
//        tab2.setOnClickListener {
//            selectItem(R.id.trophiesFragment)
//        }
//
//        tab3.setOnClickListener {
//            selectItem(R.id.menuFragment)
//        }

//        selectItem(R.id.mainFragment)

    }

    private fun inflateMenu() {

        menu = MenuBuilder(context)

        val inflater = SupportMenuInflater(context)
//        inflater.inflate(R.menu.navigation, menu)

    }

    fun selectItem(@IdRes itemId: Int) {

        Log.d("Select Item", itemId.toString())
//
//        if (selectedItemId != itemId) {
//            selectedItemId = itemId
//            onNavItemSelectedListener?.invoke(menu.findItem(itemId))
//        }

//        if (itemId == R.id.mainFragment) {
//            text0.isVisible = true
//            tab0.setBackgroundResource(R.drawable.bg_bottom_bar_selection)
//        } else {
//            text0.isVisible = false
//            tab0.background = null
//        }
//
//        if (itemId == R.id.historyFragment) {
//            text1.isVisible = true
//            tab1.setBackgroundResource(R.drawable.bg_bottom_bar_selection)
//        } else {
//            text1.isVisible = false
//            tab1.background = null
//        }
//
//        if (itemId == R.id.trophiesFragment) {
//            text2.isVisible = true
//            tab2.setBackgroundResource(R.drawable.bg_bottom_bar_selection)
//        } else {
//            text2.isVisible = false
//            tab2.background = null
//        }
//
//        if (itemId == R.id.menuFragment) {
//            text3.isVisible = true
//            tab3.setBackgroundResource(R.drawable.bg_bottom_bar_selection)
//        } else {
//            text3.isVisible = false
//            tab3.background = null
//        }
    }

    fun setOnNavigationItemSelectedListener(listener: (item: MenuItem) -> Boolean) {
        onNavItemSelectedListener = listener
    }
}