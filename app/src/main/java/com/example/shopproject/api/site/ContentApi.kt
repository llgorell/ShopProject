package com.example.shopproject.api.site

import com.example.shopproject.model.ServiceResponse
import com.example.shopproject.model.site.Blog
import com.example.shopproject.model.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("/api/content")
    suspend fun getContent():ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentByTitle(@Path("title")id:String):ServiceResponse<Content>
}