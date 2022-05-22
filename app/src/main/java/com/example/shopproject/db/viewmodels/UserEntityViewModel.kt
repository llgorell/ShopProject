package com.example.shopproject.db.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shopproject.db.models.UserEntity
import com.example.shopproject.db.repositories.UserEntityRepository

class UserEntityViewModel(application: Application) : AndroidViewModel(application) {

    private  var userRepository = UserEntityRepository(application)
     var currentUser = mutableStateOf<UserEntity?>(null)


    suspend fun addUser(userEntity: UserEntity) {
        deleteAll()
        userRepository.addUser(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        if (userEntity.id < 0) return
        userRepository.delete(userEntity)
    }

    suspend fun deleteAll() {
        userRepository.deleteAll()
    }

    suspend fun update(userEntity: UserEntity) {
        if (userEntity.id < 0) return
        userRepository.update(userEntity)
    }

    fun get(): LiveData<UserEntity> {
        return userRepository.get()
    }
}