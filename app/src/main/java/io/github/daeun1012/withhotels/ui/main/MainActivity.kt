package io.github.daeun1012.withhotels.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.viewModels
import io.github.daeun1012.withhotels.R
import io.github.daeun1012.withhotels.databinding.ActivityMainBinding
import io.github.daeun1012.withhotels.utils.InjectorUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

}
