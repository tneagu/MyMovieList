package com.kodorebi.mymovielist.core.models

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException

/**
 * Created by TNE17909 on 4/28/2020.
 * Copyright © 2019 OpenGroupe. All rights reserved.
 */
class Session {
//    companion object {
//        private val handler = Handler(Looper.getMainLooper())
//    }
//
//    private val usersDataService : UsersDataService by App.kodein.instance()
//    private val tokens : AuthTokens by App.kodein.instance()
//
//    var user: LiveData<User?> = MutableLiveData()
//        set(value) {
//            handler.post{
//                field.removeObserver(userObserver)
//                //We add our own observer so that user can be obtained
//                //without depending on other subscribers
//                value.observeForever(userObserver)
//            }
//            field = value
//        }
//
//    val authorizationFailed : LiveData<Throwable> by lazy {
//        val data = MediatorLiveData<Throwable>()
//        data.addSource(tokens.refreshTokenFailed, Observer { throwable ->
//            when(throwable){
//                is HttpException -> {
//                    if (throwable.code() == 401) {
//                        //unauthorized
//                        data.postValue(throwable)
//                    }
//                }
//                else -> throwable.printStackTrace()
//            }
//        })
//
//        data
//    }
//
//    private val userObserver = Observer<User?> {
//        print(it?.name)
//    }
}