package com.example.shopproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.example.shopproject.ui.components.product.CardShopping
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.absoluteValue

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun BasketScreen(navController: NavController, basketViewModel: BasketEntityViewModel) {
    val data = basketViewModel.dataList
    var totalPrice by mutableStateOf<Long>(0)
    val state = MutableStateFlow<Long>(0)

    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Shopping Cart",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (data.value.isEmpty()) {
            Card(shape = RoundedCornerShape(50.dp), modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = com.example.shopproject.R.drawable.empty_cart),
                    contentDescription = ""
                )
            }
        }
        LazyColumn() {
            items(data.value.size) { index ->
                CardShopping(data = data.value[index], basketViewModel, index, navController)
                /*val sum = (data.value[index].price)!! * (data.value[index].quantity)
                totalPrice += sum*/
            }
            item {
                Row() {
                        data.value.forEach {
                            state.value += (it.price)!! * (it.quantity)
                        }
                    Text(text = "Total Price =")
                    Text(text = "${state.value}")
                }
            }
            item{
                if(data.value.isNotEmpty()){
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                                  navController.navigate("paymentInfo")
                        }, elevation = ButtonDefaults.elevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 8.dp,
                            disabledElevation = 0.dp

                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                    ) {
                        Text(text = "Proced to Payment")
                    }
                }
            }
        }

    }

}




