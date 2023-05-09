package com.playground.modmelonskins.fragments.dialogs.rate

import android.os.Bundle
import android.view.View
import com.playground.modmelonskins.databinding.DialogRateBinding
import com.playground.modmelonskins.extensions.rateApp
import com.playground.modmelonskins.fragments.base.BaseDialogFragment

class DialogRateFragment: BaseDialogFragment<DialogRateBinding>(DialogRateBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setClickListeners()
        }
    }

    private fun DialogRateBinding.setClickListeners() {
        tvRate.setOnClickListener {
            requireContext().rateApp()
        }
        tvCancel.setOnClickListener {
            dismiss()
        }
    }
}