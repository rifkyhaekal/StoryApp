package com.albro.storyapp.auth.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.albro.storyapp.auth.R
import com.albro.storyapp.auth.databinding.FragmentRegisterBinding
import com.albro.storyapp.auth.register.RegisterFragmentDirections.Companion.actionRegisterFragmentToLoadingDialogFragment
import com.albro.storyapp.auth.register.RegisterFragmentDirections.Companion.actionRegisterFragmentToLoginFragment
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment
import com.albro.storyapp.core.common.ui.ErrorBottomSheetDialogFragment.Companion.TAG
import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.utils.Status
import com.albro.storyapp.core.utils.UiState
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        playAnimation()
    }

    private fun initViews() {
        with(binding) {
            btnRegister.setOnClickListener {
                lifecycleScope.launch {
                    fetchRegister(
                        edRegisterName.text.toString(),
                        edRegisterEmail.text.toString(),
                        edRegisterPassword.text.toString()
                    )
                }
            }

            tvLogin.setOnClickListener {
                findNavController().navigate(actionRegisterFragmentToLoginFragment())
            }
        }
    }

    private suspend fun fetchRegister(name: String, email: String, password: String) {
        viewModel.register(name, email, password)
            .observe(viewLifecycleOwner, ::manageRegisterResponse)
    }

    private fun manageRegisterResponse(uiState: UiState<Register>) {
        when (uiState.status) {
            Status.LOADING -> {
                findNavController().navigate(actionRegisterFragmentToLoadingDialogFragment())
            }
            Status.HIDE_LOADING -> {
                findNavController().navigateUp()
            }
            Status.SUCCESS -> {
                FancyToast.makeText(
                    requireContext(),
                    getString(R.string.register_success),
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    false
                ).show()
                findNavController().navigate(
                    actionRegisterFragmentToLoginFragment(
                        binding.edRegisterEmail.text.toString(),
                        binding.edRegisterPassword.text.toString()
                    )
                )
            }
            Status.ERROR -> {
                ErrorBottomSheetDialogFragment(
                    uiState.message
                        ?: getString(R.string.something_went_wrong)
                ).show(parentFragmentManager, TAG)
            }
        }
    }

    private fun playAnimation() {
        val registerDesc = ObjectAnimator.ofFloat(binding.tvRegisterDesc, View.ALPHA, 1f).setDuration(500)
        val edRegisterName = ObjectAnimator.ofFloat(binding.tilFullname, View.ALPHA, 1f).setDuration(500)
        val edRegisterEmail = ObjectAnimator.ofFloat(binding.tilRegisterEmail, View.ALPHA, 1f).setDuration(500)
        val edRegisterPassword = ObjectAnimator.ofFloat(binding.tilRegisterPassword, View.ALPHA, 1f).setDuration(500)
        val registerButton = ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(500)
        val llLogin = ObjectAnimator.ofFloat(binding.llLogin, View.ALPHA, 1f).setDuration(500)


        val together = AnimatorSet().apply {
            playTogether(registerButton, llLogin)
        }

        AnimatorSet().apply {
            playSequentially(registerDesc, edRegisterName, edRegisterEmail, edRegisterPassword, together)
            start()
        }
    }
}