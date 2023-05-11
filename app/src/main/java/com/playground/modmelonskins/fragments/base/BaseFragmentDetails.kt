package com.playground.modmelonskins.fragments.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentDetails<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!
    var detailsToolbarListener: DetailsToolbarListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DetailsToolbarListener) {
            detailsToolbarListener = context
        } else {
            throw RuntimeException("Activity must implement DetailsToolbarListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
