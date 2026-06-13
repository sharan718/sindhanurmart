package com.example.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.*

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Only show bottom bar for consumer home
            if (currentRoute == "home") {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationBarItem(
                        selected = currentRoute == "home",
                        onClick = {
                            navController.navigate("home") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onNavigateToDashboard = { role ->
                        val route = when (role) {
                            "admin" -> "admin"
                            "seller" -> "seller"
                            "partner" -> "partner"
                            else -> "home"
                        }
                        navController.navigate(route) {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("home") {
                ConsumerHomeScreen(
                    onNavigateToSearch = { navController.navigate("search") },
                    onNavigateToProduct = { navController.navigate("product") },
                    onNavigateToCart = { navController.navigate("cart") }
                )
            }
            composable("search") {
                SearchResultsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToProduct = { navController.navigate("product") }
                )
            }
            composable("product") {
                ProductDetailsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToCart = { navController.navigate("cart") }
                )
            }
            composable("cart") {
                CartCheckoutScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable("admin") {
                AdminDashboardScreen()
            }
            composable("seller") {
                SellerDashboardScreen()
            }
            composable("partner") {
                PartnerDashboardScreen()
            }
        }
    }
}
