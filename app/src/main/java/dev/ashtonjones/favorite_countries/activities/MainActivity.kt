package dev.ashtonjones.favorite_countries.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dev.ashtonjones.favorite_countries.R
import dev.ashtonjones.favorite_countries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var topAppToolbar: Toolbar


    // Navigation Component references
    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    private var appBarConfiguration: AppBarConfiguration? = null

    private lateinit var binding: ActivityMainBinding;


    // boolean flag to keep track of back press
    private var backPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()

        // Apply the toolbar as the Top app bar
        setSupportActionBar(binding.topAppBarToolbarMainActivity)

        initUIComponentsWithNavigation()

    }

    private fun initViews() {

        // Setup NavController
        navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?)!!
        navController = navHostFragment.navController

        // Setup AppBarConfiguration and specify HomeFragment as the only top level destination
        appBarConfiguration = AppBarConfiguration.Builder().build()
    }

    private fun initUIComponentsWithNavigation() {

        // Setup the Toolbar with Navigation
        NavigationUI.setupWithNavController(binding.topAppBarToolbarMainActivity, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Have NavigationUI handle onOptionsItemSelected with the onNavDestinationSelected helper method. If the menu item is not meant to navigate, handle with super.onOptionsItemSelected
        // The menu item options in the top_app_bar_main_menu will now navigate to the corresponding destinations, or will make the call to super if the menu item is not meant to navigate

        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }






}