package com.example.shopproject.ui.components.product

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shopproject.model.product.Product
import com.example.shopproject.viewmodels.product.ProductViewModel

@Composable
fun ProductFilterView(viewModel: ProductViewModel= hiltViewModel()) {
    val filterItem = listOf("All", "New", "Popular")
    var selectedFilter by rememberSaveable() { mutableStateOf(0) }
    LazyRow(Modifier.padding(start = 16.dp)) {
        items(filterItem.size) { index ->
            TextButton(
                modifier = Modifier.width(90.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    selectedFilter = index
                    when (selectedFilter) {
                        0 -> {
                            viewModel.getProducts(0, 6) { response ->
                            }
                        }
                        1 -> {
                            viewModel.getNewProduct { response ->
                            }
                        }
                        2 -> {
                            viewModel.getPopularProduct { response ->
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedFilter == index) Color.LightGray
                    else Color.Transparent
                )
            ) {
                Text(
                    text = filterItem[index],
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}