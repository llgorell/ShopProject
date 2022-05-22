package com.example.shopproject.ui.components.slider

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopproject.ui.components.Loading
import com.example.shopproject.viewmodels.site.SliderViewModel
import com.skydoves.landscapist.Shimmer
import com.skydoves.landscapist.ShimmerParams

@Composable
fun SliderListView(viewmodel: SliderViewModel = hiltViewModel()) {
    var data by remember {
        mutableStateOf(viewmodel.dataList)
    }
    var isLoading by remember { mutableStateOf(viewmodel.isLoading)}

    if (isLoading.value){
 Loading(modifier = Modifier.width(300.dp).height(200.dp),2)
    }else {
        LazyRow(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
            items(data.value.size) { index ->
                SliderItemView(data.value[index])
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}