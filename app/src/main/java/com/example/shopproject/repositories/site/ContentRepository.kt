package com.example.shopproject.repositories.site

import com.example.shopproject.api.site.ContentApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Content
import com.example.shopproject.model.site.Slider
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class ContentRepository @Inject constructor(private val api: ContentApi) {


    suspend fun getContent(): ServiceResponse<Content> {
        return try {
            api.getContent()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
    suspend fun getContentByTitle(title : String): ServiceResponse<Content> {
        return try {
            api.getContentByTitle(title)
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }
}