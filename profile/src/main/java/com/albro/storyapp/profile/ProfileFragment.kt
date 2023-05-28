package com.albro.storyapp.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.albro.storyapp.core.utils.NavigationHelper
import com.albro.storyapp.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            tvChangeLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
            tvLogout.setOnClickListener {
                logOutAction()
            }
        }
    }

    private fun logOutAction() {
        startActivity(
            Intent(
                requireActivity(),
                Class.forName(NavigationHelper.AUTH_ROUTE)
            )
        )
        viewModel.logOut()
        requireActivity().finish()
    }
}