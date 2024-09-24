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
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Recados
import com.example.inventory.data.RecadoRepository


class RecadoEntryViewModel(private val recadoRepository: RecadoRepository) : ViewModel() {


    var recadoUiState by mutableStateOf(RecadoUiState())
        private set


    fun updateUiState(recadoDetails: RecadoDetails) {
        recadoUiState =
            RecadoUiState(recadoDetails = recadoDetails, isEntryValid = validateInput(recadoDetails))
    }


    suspend fun saveRecado() {
        if (validateInput()) {
            recadoRepository.insertRecado(recadoUiState.recadoDetails.toRecado())
        }
    }

    private fun validateInput(uiState: RecadoDetails = recadoUiState.recadoDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && descricao.isNotBlank() && data.isNotBlank()
        }
    }
}

data class RecadoUiState(
    val recadoDetails: RecadoDetails = RecadoDetails(),
    val isEntryValid: Boolean = false
)

data class RecadoDetails(
    val id: Int = 0,
    val titulo: String = "",
    val data: String = "",
    val descricao:String = "",
)


fun RecadoDetails.toRecado(): Recados = Recados(
    id = id,
    titulo = titulo,
    descricao = descricao,
    data = data

)

fun Recados.toRecadoUiState(isEntryValid: Boolean = false): RecadoUiState = RecadoUiState(
    recadoDetails = this.toRecadoDetails(),
    isEntryValid = isEntryValid
)


fun Recados.toRecadoDetails(): RecadoDetails = RecadoDetails(
    id = id,
    titulo = titulo,
    descricao = descricao,
    data = data

)
