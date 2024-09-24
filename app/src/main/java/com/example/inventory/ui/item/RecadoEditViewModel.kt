/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.RecadoRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RecadoEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val recadoRepository: RecadoRepository
) : ViewModel() {

    var recadoUiState by mutableStateOf(RecadoUiState())
        private set

    private val RecadoId: Int = checkNotNull(savedStateHandle[RecadoEditDestination.recadoIdArg])

    init {
        viewModelScope.launch {
            recadoUiState = recadoRepository.getRecadoStream(RecadoId)
                .filterNotNull()
                .first()
                .toRecadoUiState(true)
        }
    }

    suspend fun recadoItem() {
        if (validateInput(recadoUiState.recadoDetails)) {
            recadoRepository.updateRecado(recadoUiState.recadoDetails.toRecado())
        }
    }

    fun updateUiState(recadoDetails: RecadoDetails) {
        recadoUiState =
            RecadoUiState(recadoDetails = recadoDetails, isEntryValid = validateInput(recadoDetails))
    }

    private fun validateInput(uiState: RecadoDetails = recadoUiState.recadoDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && data.isNotBlank() && descricao.isNotBlank()
        }
    }
}
