package br.com.sudosu.desafio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import br.com.sudosu.desafio.R
import br.com.sudosu.desafio.SharedPreferencesManager
import br.com.sudosu.desafio.extensions.NavigationUIExtensions
import br.com.sudosu.desafio.ui.fragments.BaseActivity

class MainActivity : AppCompatActivity(), BaseActivity {

    private var currentTabIndex = 0
    var isShowingBottomBar = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTheme(R.style.AppTheme)

        savedInstanceState?.also {
            currentTabIndex = it.getInt("tab_index")
        }

        setupNavGraph()
    }
    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_main).navigateUp()
        return true
    }

    private fun setupNavGraph() {

        val startingDestination: Int = R.id.homeFragment

        val navController = findNavController(R.id.nav_host_main)

        val graph = navController.navInflater.inflate(R.navigation.navigation_graph).apply {
            startDestination = startingDestination
        }

        navController.graph = graph

    }
    override fun setToolbarTitle(title: String) {

    }

    override fun setToolbarVisibility(isVisible: Boolean) {

    }

    override fun setBottomBarVisibility(isVisible: Boolean) {

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tab_index", currentTabIndex)
    }
}
