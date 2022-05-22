package com.example.shopproject.repositories.site

import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val api: SliderApi) {


    suspend fun getSliders(): ServiceResponse<Slider> {
        return try {
            api.getSliders()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getSliderById(id : Long): ServiceResponse<Slider> {
        return try {
            api.getSliderById(id)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}