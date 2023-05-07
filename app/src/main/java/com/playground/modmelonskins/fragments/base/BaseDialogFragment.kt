package com.playground.modmelonskins.fragments.base

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.playground.modmelonskins.R

abstract class BaseDialogFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : DialogFragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    protected open val hasBackgroundTint: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.DialogTheme)
        dialog.window?.attributes?.windowAnimations = R.style.DialogTheme_Window
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}