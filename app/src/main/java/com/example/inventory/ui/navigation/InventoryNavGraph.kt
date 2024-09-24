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

package com.example.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.home.RecadoDestination
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.item.RecadoDetailsDestination
import com.example.inventory.ui.item.RecadoDetailsScreen
import com.example.inventory.ui.item.RecadoEditDestination
import com.example.inventory.ui.item.RecadoEditScreen
import com.example.inventory.ui.item.FerramentaEntryDestination
import com.example.inventory.ui.item.RecadoEntryScreen

@Composable
fun RecadoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = RecadoDestination.route,
        modifier = modifier
    ) {
        composable(route = RecadoDestination.route) {
            HomeScreen(
                navigateToRecadoEntry = { navController.navigate(FerramentaEntryDestination.route) },
                navigateToRecadoUpdate = {
                    navController.navigate("${RecadoDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = FerramentaEntryDestination.route) {
            RecadoEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = RecadoDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RecadoDetailsDestination.recadoIdArg) {
                type = NavType.IntType
            })
        ) {
            RecadoDetailsScreen(
                RecadoToEditItem = { navController.navigate("${RecadoEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = RecadoEditDestination.routeWithArgs,
            arguments = listOf(navArgument(RecadoEditDestination.recadoIdArg) {
                type = NavType.IntType
            })
        ) {
            RecadoEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
