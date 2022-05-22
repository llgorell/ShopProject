package com.example.shopproject.modules

import com.example.shopproject.api.customer.UserApi
import com.example.shopproject.api.invoice.InvoiceApi
import com.example.shopproject.api.invoice.TransactionApi
import com.example.shopproject.api.product.ColorApi
import com.example.shopproject.api.product.ProductApi
import com.example.shopproject.api.product.ProductCategoryApi
import com.example.shopproject.api.site.BlogApi
import com.example.shopproject.api.site.ContentApi
import com.example.shopproject.api.site.SliderApi
import com.example.shopproject.config.UnSafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  ApiModule {

    @Provides
    @Singleton
    fun provideApi() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://10.0.2.2:8080/")
            .client(UnSafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideSlider() : SliderApi{
        return provideApi().create(SliderApi::class.java)
    }
    @Provides
    @Singleton
    fun provideContent() : ContentApi {
        return provideApi().create(ContentApi::class.java)
    }
    @Provides
    @Singleton
    fun provideBlog() : BlogApi {
        return provideApi().create(BlogApi::class.java)
    }
    @Provides
    @Singleton
    fun provideProductCategory() : ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }
    @Provides
    @Singleton
    fun provideProduct() : ProductApi {
        return provideApi().create(ProductApi::class.java)
    }
    @Provides
    @Singleton
    fun provideColor() : ColorApi {
        return provideApi().create(ColorApi::class.java)
    }
    @Provides
    @Singleton
    fun provideInvoice() : InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }
    @Provides
    @Singleton
    fun provideTransaction() : TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUser() : UserApi {
        return provideApi().create(UserApi::class.java)
    }
}