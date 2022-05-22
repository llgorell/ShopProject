package com.example.shopproject.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.ui.components.product.ProductCategoryListView
import com.example.shopproject.ui.components.product.ProductFilterView
import com.example.shopproject.ui.components.product.ProductListView
import com.example.shopproject.ui.components.slider.SliderListView
import com.example.shopproject.viewmodels.product.ProductViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    var productList by remember {
        mutableStateOf(viewModel.dataList)
    }
    var isLoading by remember {
        mutableStateOf(viewModel.isLoading)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item() {
            SliderListView()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item() {
            ProductCategoryListView(navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ProductFilterView()
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(
            productList.value.size
        ) { index ->
            ProductListView(productList.value[index], navController)
        }
    }
}








