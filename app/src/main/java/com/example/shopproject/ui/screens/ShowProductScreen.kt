package com.example.shopproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.db.viewmodels.BasketEntityViewModel
import com.example.shopproject.ui.components.Loading
import com.example.shopproject.viewmodels.product.ProductViewModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun ShowProductScreen(
    navController: NavController,
    productId: Long,
    basketviewmodel : BasketEntityViewModel,
    viewmodel: ProductViewModel = hiltViewModel()
) {
    var product by remember { mutableStateOf(viewmodel.product) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current
    var selectedSize by remember { mutableStateOf(-1) }
    var selectedColor by remember { mutableStateOf(-1) }

    viewmodel.getProductById(productId) { response ->
        isLoading = false
        if (response.status == "OK" && response.data?.isNotEmpty()!!) {
            viewmodel.product.value = response.data!![0]
        } else {
            Toast.makeText(context, "Error on Load Data!!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }
    if (isLoading) {
        Loading(modifier = Modifier.fillMaxSize())
    } else {
        Card(modifier = Modifier.fillMaxSize()) {
            GlideImage(
                contentScale = ContentScale.Crop,
                // CoilImage, FrescoImage
                imageModel = product.value?.image,
                // shows a shimmering effect when loading an image.
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.Gray,
                    durationMillis = 700,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                // shows an error text message when request failed.
//            failure = {
//                Text(text = "image request failed.")
//            }
            )
            Box() {
                IconButton(onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                        tint = Color.White
                    )
                }
            }
            Box(
                contentAlignment = Alignment.BottomStart, modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            ) {
                Column(modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)) {
                    Text(
                        text = product.value?.title!!,
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "${product.value?.price} T",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Text(
                        text = "Sizes",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    LazyRow() {
                        items(product.value!!.sizes?.size!!) { index ->
                            TextButton(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor =
                                    if (selectedSize == index) Color.LightGray else Color.Transparent
                                ),
                                shape = RoundedCornerShape(100.dp),
                                onClick = {
                                    selectedSize = index

                                }, modifier = Modifier
                                    .shadow(
                                        100.dp,
                                        RoundedCornerShape(100.dp),
                                        clip = true
                                    )
                                    .height(48.dp)
                                    .width(48.dp)
                            ) {
                                Text(

                                    text = product.value!!.sizes?.get(index)?.title.toString(),
                                    fontSize = 12.sp,
                                    color = if (selectedSize == index) Color.Black else Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }

                    }
                    Text(
                        text = "Color",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    LazyRow(modifier = Modifier.padding(top = 16.dp)) {
                        items(product.value!!.colors!!.size) { index ->
                            TextButton(
                                onClick = { selectedColor = index },
                                colors = ButtonDefaults.buttonColors(
                                    Color(
                                        android.graphics.Color.parseColor(
                                            "#" + product.value!!.colors!![index].hexValue
                                        )
                                    )
                                ),
                                modifier = Modifier.size(48.dp),
                                shape = RoundedCornerShape(50.dp),
                                border = BorderStroke(1.dp, Color.White)
                            ) {
                                if (selectedColor == index) {
                                    Icon(
                                        imageVector = Icons.Filled.Check, contentDescription = "",
                                        tint = if (product.value!!.colors!![index].hexValue?.lowercase(
                                                Locale.getDefault()
                                            ) == "ffffff"
                                        ) Color.Black else Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            if (selectedColor == -1){
                                Toast.makeText(context,"lotfan rang khod ra entekhab konid",Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (selectedSize == -1){
                                Toast.makeText(context,"lotfan size khod ra entekhab konid",Toast.LENGTH_SHORT).show()
                                return@Button
                            }
                            if (selectedColor != -1 && selectedSize != -1){
                                CoroutineScope(Dispatchers.IO).launch {
                                    val basket = BasketEntity(
                                        productId = productId,
                                        quantity = 1,
                                        colorId = product.value!!.colors?.get(selectedColor)!!.id!!,
                                        sizeId = product.value!!.sizes?.get(selectedSize)!!.id!!,
                                        image = product.value!!.image!!,
                                        price = product.value!!.price,
                                        title = product.value!!.title!!,
                                        colorHex = product.value!!.colors!![selectedColor].hexValue!!,
                                        size = product.value!!.sizes?.get(selectedSize)!!.title!!
                                    )
                                    basketviewmodel.addToBasket(basket = basket)
                                }
                            }
                            Toast.makeText(context,"basket added",Toast.LENGTH_SHORT).show()
                            navController.popBackStack()

                        },
                        Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(45f),
                        colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                        Text(text = "Buy Now", fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }

    }

}