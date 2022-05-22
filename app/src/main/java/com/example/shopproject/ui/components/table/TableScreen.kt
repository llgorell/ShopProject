package com.example.shopproject.ui.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shopproject.db.models.BasketEntity

@Composable
fun TableScreen(data : List<BasketEntity>) {
    val tableData = (1..data.size).mapIndexed{index, item ->

    }

    val columnNumber = 0.1f
    val columnTitle = 0.3f
    val columnPrice = 0.2f
    val columnQuantity = 0.2f
    val columnTotal = 0.2f
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        item {
            Row(Modifier.background(Color.LightGray)) {
                TableCell(text = "#", weight = columnNumber)
                TableCell(text = "Title", weight = columnTitle)
                TableCell(text = "Price", weight = columnPrice)
                TableCell(text = "Quantity", weight = columnQuantity)
                TableCell(text = "Total", weight = columnTotal)
            }
        }
        items(data.size) { index->
            val total = (data[index].price)!! * (data[index].quantity)
            Row(Modifier.fillMaxWidth()){
                TableCell(text = data[index].productId.toString(), weight = columnNumber)
                TableCell(text = data[index].title, weight = columnTitle)
                TableCell(text = data[index].price.toString(), weight = columnPrice)
                TableCell(text = data[index].quantity.toString(), weight = columnQuantity)
                TableCell(text = total.toString(), weight = columnTotal)
            }
        }
    }
}
@Composable
fun RowScope.TableCell(text : String, weight : Float) {
    Text(text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )

}