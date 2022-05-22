package com.example.shopproject.db.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.shopproject.db.OnlineShopDataBase
import com.example.shopproject.db.dao.UserEntityDao
import com.example.shopproject.db.models.UserEntity

class UserEntityRepository(application: Application) {

    private  var userDao : UserEntityDao
    private  var currentUser : LiveData<UserEntity>
    init {
        val database = OnlineShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUser = userDao.get()
    }

    suspend fun addUser(userEntity: UserEntity){
        deleteAll()
        userDao.addUser(userEntity)
    }
    suspend fun update(userEntity: UserEntity){
        userDao.update(userEntity)
    }
    suspend fun delete(userEntity: UserEntity){
        userDao.delete(userEntity)
    }
    suspend fun deleteAll(){
       return userDao.deleteAll()
    }
     fun get(): LiveData<UserEntity> {
        return userDao.get()
    }

}