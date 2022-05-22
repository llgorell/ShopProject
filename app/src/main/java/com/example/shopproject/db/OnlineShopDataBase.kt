package com.example.shopproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopproject.db.dao.BasketEntityDao
import com.example.shopproject.db.dao.UserEntityDao
import com.example.shopproject.db.models.BasketEntity
import com.example.shopproject.db.models.UserEntity

@Database(entities = [UserEntity::class,BasketEntity::class], version = 5)
abstract class OnlineShopDataBase : RoomDatabase() {

    abstract fun userDao(): UserEntityDao
    abstract fun basketDao(): BasketEntityDao

    companion object{
        private var instance : OnlineShopDataBase? = null
        fun getInstance(context: Context) : OnlineShopDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(context,
                    OnlineShopDataBase::class.java,
                    "onelineshop.db").fallbackToDestructiveMigration().build()
            }
            return  instance as OnlineShopDataBase
        }
    }
}