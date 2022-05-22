package com.example.shopproject.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.shopproject.MainActivity
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.example.shopproject.db.viewmodels.UserEntityViewModel
import com.example.shopproject.ui.components.topAppBar
import com.example.shopproject.utils.ThisApp


@Composable
fun mainScreen(mainActivity: MainActivity) {
    val context = LocalContext.current
    val navController = rememberNavController()
    var isFullScreen by remember {
        mutableStateOf(false)

    }
    val basketViewModel =
        ViewModelProvider(mainActivity)[BasketEntityViewModel::class.java]
    basketViewModel.getAllBasketLive().observe(mainActivity) {
        basketViewModel.dataList.value = it
    }

    val userEntityViewModel = ViewModelProvider(mainActivity)[UserEntityViewModel::class.java]
    userEntityViewModel.get().observe(mainActivity){
        userEntityViewModel.currentUser.value = it
    }
    Scaffold(
        topBar = { if (!isFullScreen) topAppBar(navController = navController, basketViewModel,userEntityViewModel) }
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                isFullScreen = false
                HomeScreen(navController = navController)
            }
            composable("basket") {
                isFullScreen = true
                BasketScreen(navController = navController, basketViewModel)
            }
            composable("paymentInfo") {
                isFullScreen = true
                UserPaymentScreen(
                    navController = navController,
                    mainActivity = mainActivity,
                    basketEntityViewModel = basketViewModel
                )
            }
            composable("login") {
                isFullScreen = true
                ProfileScreen(navController = navController,userEntityViewModel)
            }
            composable("dashboard") {
                isFullScreen = true
                DashboardScreen(navController,userEntityViewModel)
            }
            composable("products/{categoryId}/{title}",
                arguments = listOf(
                    navArgument("categoryId") { type = NavType.LongType },
                    navArgument("title") { type = NavType.StringType }
                )
            )
            { backstack ->
                isFullScreen = false
                val categoryId = backstack.arguments?.getLong("categoryId")
                val title = backstack.arguments?.getString("title")
                ThisApp.categoryId = categoryId!!
                ProductsScreen(categoryId, title!!, navController = navController)


            }
            composable(
                "showProduct/{product}",
                arguments = listOf(
                    navArgument("product") { type = NavType.LongType }
                )
            ) { backstack ->
                backstack.arguments?.getLong("product").let { productId ->
                    isFullScreen = true

                    ShowProductScreen(navController, productId!!, basketViewModel)
                }

            }
            //example for deep link + added 2 category in manifest Defualt+browsable + 1 action view + data scheme + host
            composable("invoice/{id}",
                deepLinks = listOf(navDeepLink { uriPattern = "app://onlineshopholosen.ir/{id}" })
            ) { backStackEntry ->
                InvoiceScreen(navController, backStackEntry.arguments?.getLong("id"))
            }
        }
    }
}