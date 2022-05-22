package com.example.shopproject.ui.components.product

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.ui.components.Loading
import com.example.shopproject.viewmodels.product.CategoryViewModel

@Composable
fun ProductCategoryListView(navController: NavController,viewmodel: CategoryViewModel = hiltViewModel()) {

    var dataList by remember {
        mutableStateOf(viewmodel.listDataState)
    }
    var isLoading by remember {
        mutableStateOf(viewmodel.isLoading)
    }
    if (isLoading.value){
        Loading(modifier = Modifier.height(146.dp).width(146.dp),3)
    }else {
        LazyRow(Modifier.padding(start = 16.dp, end = 8.dp)) {
            items(dataList.value.size) { index ->
                CategoryItemView(dataList.value[index],navController)
            }
        }
    }
}

