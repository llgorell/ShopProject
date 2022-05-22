package com.example.shopproject.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.ui.components.Loading
import com.example.shopproject.ui.components.product.ProductListView
import com.example.shopproject.viewmodels.product.ProductByCategoryViewModel

@Composable
fun ProductsScreen(categoryId: Long?,title : String, navController: NavController, viewmodel:ProductByCategoryViewModel = hiltViewModel()) {

    val dataList by remember { mutableStateOf(viewmodel.dataList) }
    var isLoading by remember {
        mutableStateOf(viewmodel.isLoading)
    }


        LazyColumn() {
            /*     item(){
                Text(text = title)
            }*/
            items(dataList.value.size) { index ->
                viewmodel.onScrollPosition(index)
                if (index +1 >=dataList.value.size-1 && !viewmodel.isLoading.value ) {
                    viewmodel.nextPage()
                }
                ProductListView(dataList.value[index], navController)
            }
        }
   /*     if (isLoading.value) {
            Box(contentAlignment = BottomCenter, modifier = Modifier.fillMaxWidth()) {
                Loading(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }*/

    }


