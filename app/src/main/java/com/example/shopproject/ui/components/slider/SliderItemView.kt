package com.example.shopproject.ui.components.slider

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopproject.model.site.Slider
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderItemView(slider: Slider) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        elevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = {/*TODO onclick link */ }
    ) {

        Box {
            GlideImage( // CoilImage, FrescoImage
                imageModel = slider.image,
                // shows a shimmering effect when loading an image.
              /*  shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.Gray,
                    durationMillis = 700,
                    dropOff = 0.65f,
                    tilt = 20f
                )*/
                // shows an error text message when request failed.
                failure = {
                    Text(text = "image request failed.")
                })
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxSize()
                , contentAlignment = Alignment.BottomStart
            ) {

                Column{
                    Text(
                        text = slider.title!!,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        maxLines = 1
                    )
                    Text(text = slider.subTitle!!,
                        color = Color.Gray,
                        fontSize = 16.sp)
                }

            }
        }

    }

}