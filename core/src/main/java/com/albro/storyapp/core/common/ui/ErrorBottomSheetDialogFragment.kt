package com.albro.storyapp.core.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import com.albro.storyapp.core.databinding.FragmentErrorBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorBottomSheetDialogFragment(private val errorMessage: String) : BottomSheetDialogFragment() {

    private val binding: FragmentErrorBottomSheetDialogBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvErrorMessage.text = errorMessage
    }

    companion object {
        const val TAG = "ErrorBottomSheetDialogFragment"
    }
}