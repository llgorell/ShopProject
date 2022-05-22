package com.example.shopproject.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shopproject.ui.utils.NavigationItem

@Composable
fun BottomNavigationView(navController: NavController){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRout = navBackStackEntry?.destination?.route
    val items = arrayListOf(
        NavigationItem.home,
        NavigationItem.search,
        NavigationItem.profile)
    
    BottomAppBar(backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
    contentColor = Gray) {
        items.forEach {
            BottomNavigationItem(icon = { Icon(painter = painterResource(id = it.icon), contentDescription = it.title)}
                ,
                selected = currentRout == it.route,
                selectedContentColor = Red,
                unselectedContentColor = Gray,
                onClick = {
                          navController.navigate(it.route){
                              navController.graph.startDestinationRoute?.let { route ->
                                  popUpTo(route = route){
                                      saveState = true
                                  }
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                          },
               )

        }
    }

}