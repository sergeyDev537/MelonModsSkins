package com.playground.modmelonskins.fragments.skins

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.playground.modmelonskins.adapters.skins.SkinsAdapter
import com.playground.modmelonskins.databinding.FragmentSkinsBinding
import com.playground.modmelonskins.extensions.showSnackBar
import com.playground.modmelonskins.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SkinsFragment : BaseFragment<FragmentSkinsBinding>(FragmentSkinsBinding::inflate) {

    private val skinsViewModel: SkinsViewModel by viewModels()
    private val skinsAdapter by lazy {
        SkinsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setAdapter()
        }
        skinsViewModel.apply {
            setObservable()
        }
    }

    private fun FragmentSkinsBinding.setAdapter() {
        rvSkins.adapter = skinsAdapter
        skinsAdapter.clickItemSkins = {id ->
            id?.let {
                findNavController().navigate(
                    SkinsFragmentDirections.actionNavigationSkinsToDetailsFragment(it)
                )
            } ?: binding.root.showSnackBar("ERROR")
        }
    }

    private fun SkinsViewModel.setObservable() {
        listSkins.observe(viewLifecycleOwner){
            Log.d("TAGING", "LIST SKINS FRAGMENT SIZE: ${it.size}")
            skinsAdapter.submitList(it)
            visibleProgress(false)
        }
    }

    private fun visibleProgress(boolean: Boolean){
        binding.apply {
            progressSkins.isVisible = boolean
            rvSkins.isGone = boolean
        }
    }

}