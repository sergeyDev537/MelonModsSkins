package com.playground.modmelonskins.fragments.details

import androidx.lifecycle.ViewModel
import com.playground.modmelonskins.domain.usecases.GetItemModUseCase
import com.playground.modmelonskins.domain.usecases.GetItemSkinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getItemModUseCase: GetItemModUseCase,
    private val getItemSkinUseCase: GetItemSkinUseCase
) : ViewModel() {



}