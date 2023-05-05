package com.playground.modmelonskins.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.playground.modmelonskins.databinding.FragmentDetailsBinding
import com.playground.modmelonskins.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val args by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAGING", "ARGS: ${args.id}")
    }
}