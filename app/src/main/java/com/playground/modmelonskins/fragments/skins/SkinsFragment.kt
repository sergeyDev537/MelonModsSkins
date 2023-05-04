package com.playground.modmelonskins.fragments.skins

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.playground.modmelonskins.databinding.FragmentSkinsBinding
import com.playground.modmelonskins.fragments.base.BaseFragment

class SkinsFragment : BaseFragment<FragmentSkinsBinding>(FragmentSkinsBinding::inflate) {

    private val skinsViewModel: SkinsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}