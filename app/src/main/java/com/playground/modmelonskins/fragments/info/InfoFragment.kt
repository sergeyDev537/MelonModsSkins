package com.playground.modmelonskins.fragments.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.playground.modmelonskins.databinding.FragmentInfoBinding
import com.playground.modmelonskins.fragments.base.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    private val infoViewModel: InfoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}