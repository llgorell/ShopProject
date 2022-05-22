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
import com.example.shopproject.repositories.customer.UserRepository
import com.example.shopproject.repositories.invoice.InvoiceRepository
import com.example.shopproject.repositories.invoice.TransactionRepository
import com.example.shopproject.repositories.product.ColorRepository
import com.example.shopproject.repositories.product.ProductCategoryRepository
import com.example.shopproject.repositories.product.ProductRepository
import com.example.shopproject.repositories.site.BlogRepository
import com.example.shopproject.repositories.site.ContentRepository
import com.example.shopproject.repositories.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSliderRepository(api : SliderApi) = SliderRepository(api)

    @Provides
    @Singleton
    fun provideBlogRepository(api : BlogApi) = BlogRepository(api)

    @Provides
    @Singleton
    fun provideContentRepository(api : ContentApi) = ContentRepository(api)

    @Provides
    @Singleton
    fun provideColorRepository(api : ColorApi) = ColorRepository(api)

    @Provides
    @Singleton
    fun provideProductCategoryRepository(api : ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideProductRepository(api : ProductApi) = ProductRepository(api)

    @Provides
    @Singleton
    fun provideInvoiceRepository(api : InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api : TransactionApi) = TransactionRepository(api)

    @Provides
    @Singleton
    fun provideUserRepository(api : UserApi) = UserRepository(api)
}