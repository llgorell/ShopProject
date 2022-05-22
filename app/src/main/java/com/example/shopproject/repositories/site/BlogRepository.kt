package com.example.shopproject.repositories.site

import com.example.shopproject.api.site.BlogApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Blog
import com.example.shopproject.model.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class BlogRepository @Inject constructor(private val api: BlogApi) {


    suspend fun getBlog(): ServiceResponse<Blog> {
        return try {
            api.getBlog()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getBlogById(id : Long): ServiceResponse<Blog> {
        return try {
            api.getBlogById(id)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}