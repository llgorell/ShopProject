package com.example.shopproject.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.shopproject.db.viewmodels.UserEntityViewModel

@Composable
fun DashboardScreen(navController: NavHostController, userEntityViewModel: UserEntityViewModel) {
    Text(text = "${userEntityViewModel.currentUser.value!!.firstName}")
}