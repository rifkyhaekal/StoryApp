package com.albro.storyapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.albro.storyapp.R
import com.albro.storyapp.core.utils.NavigationHelper.AUTH_ROUTE
import com.albro.storyapp.core.utils.NavigationHelper.HOME_ROUTE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        lifecycleScope.launch {
            delay(4500)
            withContext(Dispatchers.Main) {
                mainViewModel.readLoginState().observe(this@MainActivity) { state ->
                    if (state) navigateToHome() else navigateToAuth()
                }
            }
        }
    }

    private fun navigateToAuth() {
        startActivity(Intent(this, Class.forName(AUTH_ROUTE)))
        this.finish()
    }

    private fun navigateToHome() {
        startActivity(Intent(this, Class.forName(HOME_ROUTE)))
        this.finish()
    }
}