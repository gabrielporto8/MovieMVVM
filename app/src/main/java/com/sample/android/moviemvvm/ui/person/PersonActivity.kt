package com.sample.android.moviemvvm.ui.person

import android.os.Bundle
import com.sample.android.moviemvvm.R
import com.sample.android.moviemvvm.util.addFragmentToActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PersonActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var personFragment: PersonFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as PersonFragment? ?: personFragment.also {
                addFragmentToActivity(it, R.id.fragment_container)
            }
        }
    }
}

const val PERSON_WRAPPER = "person"
