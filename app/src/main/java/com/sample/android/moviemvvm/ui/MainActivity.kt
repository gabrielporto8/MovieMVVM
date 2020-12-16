package com.sample.android.moviemvvm.ui

import android.app.ActivityOptions
import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.sample.android.moviemvvm.util.NavType
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.ui.item.movie.HighRateMoviesFragment
import com.sample.android.moviemvvm.ui.item.movie.PopularMoviesFragment
import com.sample.android.moviemvvm.ui.item.movie.UpcomingMoviesFragment
import com.sample.android.moviemvvm.ui.search.EXTRA_NAV_TYPE
import com.sample.android.moviemvvm.ui.search.SearchActivity
import com.sample.android.moviemvvm.ui.item.tvshow.HighRateTVShowFragment
import com.sample.android.moviemvvm.ui.item.tvshow.LatestTVShowFragment
import com.sample.android.moviemvvm.ui.item.tvshow.PopularTVShowFragment
import com.sample.android.moviemvvm.util.addFragmentToActivity
import com.sample.android.moviemvvm.util.replaceFragmentInActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), SensorEventListener {

    @Inject
    lateinit var popularMoviesFragment: PopularMoviesFragment

    @Inject
    lateinit var highRateMoviesFragment: HighRateMoviesFragment

    @Inject
    lateinit var upcomingMoviesFragment: UpcomingMoviesFragment

    @Inject
    lateinit var popularTVShowFragment: PopularTVShowFragment

    @Inject
    lateinit var highRateTVShowFragment: HighRateTVShowFragment

    @Inject
    lateinit var latestTVShowFragment: LatestTVShowFragment

    private lateinit var viewModel: MainViewModel
    private var sensorManager: SensorManager? = null
    private var color = false

    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        setSupportActionBar(toolbar)

        // Add movie detailFragment if this is first creation
        if (savedInstanceState == null) {
            addFragmentToActivity(popularMoviesFragment, R.id.fragment_container)
            nav_view.setCheckedItem(R.id.action_movies)
        }

        nav_view.setNavigationItemSelectedListener { item ->
            drawer_layout.closeDrawer(GravityCompat.START)
            bottom_navigation.selectedItemId = R.id.action_popular
            val fragment = when (item.itemId) {
                R.id.action_movies -> {
                    viewModel.setHeadlineAndNavType(R.string.menu_movies, NavType.MOVIES)
                    popularMoviesFragment
                }
                R.id.action_tv_series -> {
                    viewModel.setHeadlineAndNavType(R.string.menu_tv_series, NavType.TV_SERIES)
                    popularTVShowFragment
                }
                else -> throw RuntimeException("Unknown navType to replace fragment")
            }
            replaceFragmentInActivity(fragment, R.id.fragment_container)
            true
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar,
                R.string.open_nav_drawer, R.string.close_nav_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        var fragment: Fragment

        bottom_navigation.apply {

            setOnNavigationItemSelectedListener { item ->

                when (item.itemId) {
                    R.id.action_popular -> {
                        fragment = when (getNavType()) {
                            NavType.MOVIES -> popularMoviesFragment
                            NavType.TV_SERIES -> popularTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    R.id.action_upcoming -> {
                        fragment = when (getNavType()) {
                            NavType.MOVIES -> upcomingMoviesFragment
                            NavType.TV_SERIES -> latestTVShowFragment
                            else -> throw RuntimeException("Unknown navType")
                        }
                    }
                    else -> throw RuntimeException("Unknown sortType to replace fragment")
                }
                replaceFragmentInActivity(fragment, R.id.fragment_container)
                true
            }

            sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.headline.observe(this, Observer {
            title = it
        })
    }

    private fun getAccelerometer(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]

        val accelerationSquareRoot = (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

        if (accelerationSquareRoot >= 3) {
            Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT).show()
            if (color) {
                toolbar.setBackgroundColor(resources.getColor(R.color.colorAccent))
            } else {
                toolbar.setBackgroundColor(Color.YELLOW)
            }
            color = !color
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchMenuView: View = toolbar.findViewById(R.id.action_search)
                val options = ActivityOptions.makeSceneTransitionAnimation(this,
                        searchMenuView, getString(R.string.transition_search_back)).toBundle()

                val intent = Intent(this,
                        SearchActivity::class.java).apply {
                    action = ACTION_SEARCH
                    putExtras(Bundle().apply {
                        putParcelable(EXTRA_NAV_TYPE, getNavType())
                    })
                }
                startActivity(intent, options)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun getNavType() = viewModel.currentType.value
}