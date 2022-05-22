package com.example.shopproject.ui.components.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shopproject.R
import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardShopping(data: BasketEntity, basketViewModel: BasketEntityViewModel, index: Int,navController: NavController) {

    var quantity by remember { mutableStateOf(basketViewModel.dataList.value[index].quantity)}
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row() {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(116.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    navController.navigate("showProduct/${data.productId}")
                }
            ) {
                GlideImage( // CoilImage, FrescoImage
                    imageModel = data.image,
                    // shows a shimmering effect when loading an image.
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.Gray,
                        durationMillis = 700,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })
            }
            Column(modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .padding(start = 8.dp)) {
                Text(text = data.title)
                Text(text = "${data.price} T")
                Text(text = data.size)
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        Color(
                            android.graphics.Color.parseColor(
                                "#" + data.colorHex
                            )
                        )
                    ),
                    modifier = Modifier.size(36.dp),
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, Color.White), onClick = {

                    }
                ) {

                }
            }
            Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(28.dp)


                ) {
                    IconButton(onClick = {
                        //decrement quantity
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.decerementQuantity(data)

                        }
                        quantity--
                    }) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_circle_outline_24), contentDescription = "")
                    }
                    Text(modifier = Modifier
                        .align(CenterHorizontally)
                        .height(24.dp),text =quantity.toString())
                    IconButton(onClick = {
                        //add quantity
                        CoroutineScope(Dispatchers.IO).launch {
                            basketViewModel.incerementQuantity(data)
                        }
                        quantity++
                    }) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
                    }
                }
                IconButton(onClick = {
                    //Delete
                    CoroutineScope(Dispatchers.IO).launch {
                        basketViewModel.delete(data)
                    }
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "", tint = Color.Red)
                }
            }

            }
        }
    }
