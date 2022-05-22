package com.example.shopproject.repositories.product

import com.example.shopproject.api.product.ColorApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.product.ProductColor
import com.example.shopproject.model.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {


    suspend fun getColors(): ServiceResponse<ProductColor> {
        return try {
            api.getColor()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getColorById(id : Long): ServiceResponse<ProductColor> {
        return try {
            api.getColorById(id)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}