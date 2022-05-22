package com.example.shopproject.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.db.repositories.BasketEntityRepository


class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {
    private var basketRepository = BasketEntityRepository(application)
    var dataList = mutableStateOf<List<BasketEntity>>(listOf())

    private suspend fun insert(basketEntity: BasketEntity) {
        basketRepository.insert(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        if (basketEntity.id < 0) return
        basketRepository.delete(basketEntity)
    }

    suspend fun deleteAll() {
        basketRepository.deleteAll()
    }

    private suspend fun update(basketEntity: BasketEntity) {
        if (basketEntity.id < 0) return
        basketRepository.update(basketEntity)
    }

    suspend fun getAllBasket(): List<BasketEntity> {
        return basketRepository.getAll()
    }

     fun getAllBasketLive(): LiveData<List<BasketEntity>> {
        return basketRepository.getAllLive()
    }

    suspend fun addToBasket(basket: BasketEntity) {
        if (dataList.value.any { x ->
                x.productId == basket.productId &&
                        x.colorId == basket.colorId &&
                        x.sizeId == basket.sizeId
            }) {
            val oldBasket = dataList.value.first { x ->
                x.productId == basket.productId &&
                        x.colorId == basket.colorId &&
                        x.sizeId == basket.sizeId
            }
            oldBasket.quantity++
            update(oldBasket)
        } else {
            insert(basket)
        }
    }
    suspend fun incerementQuantity(basketEntity: BasketEntity) {
        if (basketEntity.id < 0) return
        basketRepository.incerementQuantity(basketEntity)
    }
    suspend fun decerementQuantity(basketEntity: BasketEntity) {
        if (basketEntity.id < 0) return
        basketRepository.decerementQuantity(basketEntity)
    }
}