package com.example.shopproject.api.site

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {
    @GET("/api/slider")
    suspend fun getSliders() : ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSliderById(@Path("id")id : Long):ServiceResponse<Slider>
}