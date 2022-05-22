package com.example.shopproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.example.shopproject.db.viewmodels.UserEntityViewModel

@Composable
fun topAppBar(
    navController: NavController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel
) {
    TopAppBar(
        title = { Text(text = "Online Shop") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            Box(contentAlignment = Alignment.BottomEnd) {
                IconButton(onClick = { navController.navigate("basket") }) {
                    Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "")
                }
                if (basketViewModel.dataList.value.isNotEmpty()) {
                    Card(
                        shape = RoundedCornerShape(25.dp), modifier = Modifier
                            .size(15.dp), backgroundColor = Color.Red
                    ) {
                        Text(
                            text = "${basketViewModel.dataList.value.size}",
                            color = Color.White,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            IconButton(onClick = {
                if (userEntityViewModel.currentUser.value == null){
                    navController.navigate("login")
                }else{
                    navController.navigate("dashboard")
                }

            }) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
            }
        })
}