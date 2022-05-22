package com.example.shopproject.db.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.shopproject.db.OnlineShopDataBase
import com.example.shopproject.db.dao.BasketEntityDao
import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.repositories.base.BaseRepository
import javax.inject.Inject

class BasketEntityRepository(application: Application,)  {

    private var basketDao: BasketEntityDao
    private  var basketListLive: LiveData<List<BasketEntity>>



    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
        basketListLive = basketDao.getAllLive()
    }

    suspend fun insert(basketEntity: BasketEntity) {
        basketDao.insert(basketEntity)
    }

    suspend fun update(basketEntity: BasketEntity) {
        basketDao.update(basketEntity)
    }

    suspend fun delete(basketEntity: BasketEntity) {
        basketDao.delete(basketEntity)
    }

    suspend fun deleteAll() {
        return basketDao.deleteAll()
    }
   suspend  fun getAll(): List<BasketEntity> {
        return basketDao.getAll()
    }

    fun getAllLive(): LiveData<List<BasketEntity>> {
        return basketListLive
    }
    suspend fun incerementQuantity(basketEntity: BasketEntity) {
        basketEntity.quantity++
        update(basketEntity)
    }
    suspend fun decerementQuantity(basketEntity: BasketEntity) {
        basketEntity.quantity--
        if (basketEntity.quantity <= 0) delete(basketEntity)
        else update(basketEntity)

    }

}