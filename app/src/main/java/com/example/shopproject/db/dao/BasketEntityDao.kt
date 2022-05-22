package com.example.shopproject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopproject.db.models.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(BasketEntity: BasketEntity)

    @Delete
    fun delete(BasketEntity: BasketEntity)

    @Update
    fun update(BasketEntity: BasketEntity)

    @Query("select * from BasketEntity")
    fun getAll(): List<BasketEntity>

    @Query("delete  from BasketEntity")
    fun deleteAll()

    @Query("select * from BasketEntity")
    fun getAllLive(): LiveData<List<BasketEntity>>
}