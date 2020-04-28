package com.kodorebi.mymovielist.db.objectbox.extensions

import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.query.Query

fun <T> Query<T>.toLiveData() : ObjectBoxLiveData<T> {
    return ObjectBoxLiveData(this)
}