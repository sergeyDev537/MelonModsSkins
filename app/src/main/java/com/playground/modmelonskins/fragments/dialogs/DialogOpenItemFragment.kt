package com.playground.modmelonskins.fragments.dialogs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.R
import com.playground.modmelonskins.databinding.DialogOpenItemBinding
import com.playground.modmelonskins.fragments.base.BaseDialogFragment

class DialogOpenItemFragment :
    BaseDialogFragment<DialogOpenItemBinding>(DialogOpenItemBinding::inflate) {

    private val args by navArgs<DialogOpenItemFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setData()
            setClickListeners()
        }
    }

    private fun DialogOpenItemBinding.setData() {
        titleDialogInstall.text = String.format(
            getString(R.string.want_to_install),
            args.type
        )
    }

    private fun DialogOpenItemBinding.setClickListeners() {
        buttonInstall.setOnClickListener {
            findNavController().navigate(
                DialogOpenItemFragmentDirections.actionDialogOpenItemFragmentToDetailsFragment(
                    type = args.type,
                    id = args.id
                )
            )
        }
    }
}