package com.albro.storyapp.auth.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.albro.storyapp.auth.R
import com.albro.storyapp.auth.databinding.FragmentLoginBinding
import com.albro.storyapp.auth.login.LoginFragmentDirections.Companion.actionLoginFragmentToLoadingDialogFragment
import com.albro.storyapp.auth.login.LoginFragmentDirections.Companion.actionLoginFragmentToRegisterFragment
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment.Companion.TAG
import com.albro.storyapp.core.domain.models.Login
import com.albro.storyapp.core.utils.NavigationHelper.HOME_ROUTE
import com.albro.storyapp.core.utils.Status
import com.albro.storyapp.core.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val loginViewModel: LoginViewModel by viewModels()
    private val args: LoginFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        playAnimation()
    }

    private fun initViews() {
        with(binding) {
            if (!args.email.isNullOrEmpty()) {
                edLoginEmail.setText(args.email)
                edLoginPassword.setText((args.password))
            }

            btnLogin.setOnClickListener {
                lifecycleScope.launch {
                    fetchLogin(edLoginEmail.text.toString(), edLoginPassword.text.toString())
                }
            }

            tvRegister.setOnClickListener {
                findNavController().navigate(actionLoginFragmentToRegisterFragment())
            }
        }
    }

    private suspend fun fetchLogin(email: String, password: String) {
        loginViewModel.login(email, password).observe(viewLifecycleOwner, ::manageLoginResponse)
    }

    private fun manageLoginResponse(loginState: UiState<Login>) {
        when (loginState.status) {
            Status.LOADING -> {
                findNavController().navigate(actionLoginFragmentToLoadingDialogFragment())
            }
            Status.HIDE_LOADING -> {
                findNavController().navigateUp()
            }
            Status.SUCCESS -> {
                loginViewModel.loginSuccessful()
                loginState.data?.let { loginViewModel.saveTokenKey(it.token) }
                startActivity(Intent(requireActivity(), Class.forName(HOME_ROUTE)))
                requireActivity().finish()
            }
            Status.ERROR -> {
                ErrorBottomSheetDialogFragment(
                    loginState.message ?: getString(com.albro.storyapp.core.R.string.something_went_wrong)
                ).show(parentFragmentManager, TAG)
            }
        }
    }

    private fun playAnimation() {
        val loginDesc = ObjectAnimator.ofFloat(binding.tvLoginDesc, View.ALPHA, 1f).setDuration(500)
        val edLoginEmail = ObjectAnimator.ofFloat(binding.tilLoginEmail, View.ALPHA, 1f).setDuration(500)
        val edLoginPassword = ObjectAnimator.ofFloat(binding.tilLoginPassword, View.ALPHA, 1f).setDuration(500)
        val loginButton = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val llRegister = ObjectAnimator.ofFloat(binding.llRegister, View.ALPHA, 1f).setDuration(500)


        val together = AnimatorSet().apply {
            playTogether(loginButton, llRegister)
        }

        AnimatorSet().apply {
            playSequentially(loginDesc, edLoginEmail, edLoginPassword, together)
            start()
        }
    }
}