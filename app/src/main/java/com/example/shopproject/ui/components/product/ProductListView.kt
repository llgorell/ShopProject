package com.example.shopproject.ui.components.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.example.shopproject.model.product.Product
import com.google.gson.Gson
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListView(product: Product, navController: NavController) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
        .height(200.dp)
        .shadow(
            elevation = 8.dp,
            shape = RoundedCornerShape(20.dp),
            clip = true
        ),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate("showProduct/${product.id}")
        }
    ) {
        GlideImage( // CoilImage, FrescoImage
            imageModel = product.image,
            // shows a shimmering effect when loading an image.
   /*         shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.Gray,
                durationMillis = 700,
                dropOff = 0.65f,
                tilt = 20f
            ),*/
            // shows an error text message when request failed.
//            failure = {
//                Text(text = "image request failed.")
//            }
        )
        Box(contentAlignment = Alignment.BottomStart, modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)) {
            Column() {
                Text(text = product.title!!,
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold)
                Text(text = "${product.price} T",
                    color = Color.White,
                    fontSize = 20.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold)
            }

        }

    }
    Spacer(modifier = Modifier.width(8.dp))
    }
