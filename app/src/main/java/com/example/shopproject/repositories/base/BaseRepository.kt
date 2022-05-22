package com.example.shopproject.repositories.base

open class BaseRepository {
    protected fun prapairToken(token :String) : String{
        var fixedToken = token
        if (fixedToken.lowercase().startsWith("bearer")){
            fixedToken = "bearer+$token"
        }
        return fixedToken
    }
}