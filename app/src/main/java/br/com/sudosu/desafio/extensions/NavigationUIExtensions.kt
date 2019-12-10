package br.com.sudosu.desafio.extensions

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDestination

/**
 * Sets up a [BottomNavigationView] for use with a [NavController]. This will call
 * [.onNavDestinationSelected] when a menu item is selected. The
 * selected item in the BottomNavigationView will automatically be updated when the destination
 * changes.
 *
 * @param bottomNavigationView The BottomNavigationView that should be kept in sync with
 * changes to the NavController.
 * @param navController The NavController that supplies the primary menu.
 * Navigation actions on this NavController will be reflected in the
 * selected item in the BottomNavigationView.
 */
object NavigationUIExtensions {
//    fun setupGlicoNavigationWithNavController(
//        activity: MainActivity,
//        bottomNavigationView: GlicoBottomNavigationView,
//        navController: NavController
//    ) {
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            onNavDestinationSelected(
//                item,
//                navController
//            )
//        }
//
//        val weakReference = WeakReference(bottomNavigationView)
//        navController.addOnDestinationChangedListener(
//            object : NavController.OnDestinationChangedListener {
//                override fun onDestinationChanged(
//                    controller: NavController,
//                    destination: NavDestination, arguments: Bundle?
//                ) {
//
//                    if(!activity.isShowingBottomBar)
//                        return
//
//                    val view = weakReference.get()
//                    if (view == null) {
//                        navController.removeOnDestinationChangedListener(this)
//                        return
//                    }
//                    val menu = view.menu
//                    var h = 0
//                    val size = menu.size()
//                    while (h < size) {
//                        val item = menu.getItem(h)
//                        if (matchDestination(destination, item.itemId)) {
////                            item.isChecked = true
//                            view.selectItem(item.itemId)
//                        }
//                        h++
//                    }
//                }
//            })
//    }

    /**
     * Determines whether the given `destId` matches the NavDestination. This handles
     * both the default case (the destination's id matches the given id) and the nested case where
     * the given id is a parent/grandparent/etc of the destination.
     */
    internal/* synthetic access */ fun matchDestination(
        destination: NavDestination,
        @IdRes destId: Int
    ): Boolean {
        var currentDestination: NavDestination? = destination
        while (currentDestination!!.id != destId && currentDestination.parent != null) {
            currentDestination = currentDestination.parent
        }
        return currentDestination.id == destId
    }

}