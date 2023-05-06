package com.playground.modmelonskins.fragments.mods

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.playground.modmelonskins.adapters.mods.ModsAdapter
import com.playground.modmelonskins.databinding.FragmentModsBinding
import com.playground.modmelonskins.extensions.showSnackBar
import com.playground.modmelonskins.firebase.FirebaseManager
import com.playground.modmelonskins.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModsFragment : BaseFragment<FragmentModsBinding>(FragmentModsBinding::inflate) {

    private val modsViewModel: ModsViewModel by viewModels()
    private val modsAdapter by lazy {
        ModsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visibleProgress(true)
        binding.apply {
            setAdapter()
        }
        modsViewModel.apply {
            setObservable()
        }
    }

    private fun FragmentModsBinding.setAdapter() {
        rvMods.adapter = modsAdapter
        modsAdapter.clickItemMods = {id ->
            id?.let {
                findNavController().navigate(
                    ModsFragmentDirections.actionNavigationModsToDetailsFragment(
                        type = FirebaseManager.FILE_MODS_JSON,
                        id = it
                    )
                )
            } ?: binding.root.showSnackBar("ERROR")
        }
    }

    private fun ModsViewModel.setObservable() {
        listMods.observe(viewLifecycleOwner){
            Log.d("TAGING", "LIST MODS FRAGMENT SIZE: ${it.size}")
            modsAdapter.submitList(it)
            visibleProgress(false)
        }
    }

    private fun visibleProgress(boolean: Boolean){
        binding.apply {
            progressMods.isVisible = boolean
            rvMods.isGone = boolean
        }
    }
}