package com.kodorebi.mymovielist.db.domain

import kotlin.reflect.KClass

interface Db {
    fun <T : Any> get(key: String, aClass: KClass<T>) : T?
    fun <T : Any> set(key: String, value: T?)
}