package com.albro.storyapp.core.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.dialogfragment.viewBinding
import androidx.fragment.app.DialogFragment
import com.albro.storyapp.core.databinding.FragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

    private val binding: FragmentLoadingDialogBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireDialog().window?.setBackgroundDrawableResource(android.R.color.transparent)
        isCancelable = false
        return binding.root
    }

    companion object {
        const val TAG = "LoadingDialogFragment"
    }
}