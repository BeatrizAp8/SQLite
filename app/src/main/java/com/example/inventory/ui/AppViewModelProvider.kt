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

package com.example.inventory.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventory.recadosApplication
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.item.RecadoDetailsViewModel
import com.example.inventory.ui.item.RecadoEditViewModel
import com.example.inventory.ui.item.RecadoEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            RecadoEditViewModel(
                this.createSavedStateHandle(),
                recadosApplication().container.recadoRepository
            )
        }

        initializer {
            RecadoEntryViewModel(recadosApplication().container.recadoRepository)
        }


        initializer {
            RecadoDetailsViewModel(
                this.createSavedStateHandle(),
                recadosApplication().container.recadoRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
            HomeViewModel(recadosApplication().container.recadoRepository)
        }
    }
}

fun CreationExtras.recadosApplication(): recadosApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as recadosApplication)
