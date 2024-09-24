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

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Recados] from a given data source.
 */
interface RecadoRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllRecadoStream(): Flow<List<Recados>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getRecadoStream(id: Int): Flow<Recados?>

    /**
     * Insert recados in the data source
     */
    suspend fun insertRecado(recados: Recados)

    /**
     * Delete recados from the data source
     */
    suspend fun deleteRecado(recados: Recados)

    /**
     * Update recados in the data source
     */
    suspend fun updateRecado(recados: Recados)
}


