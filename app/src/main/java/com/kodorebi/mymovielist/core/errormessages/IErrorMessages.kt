package com.kodorebi.mymovielist.core.errormessages

import android.content.Context

interface IErrorMessages {
    fun getMessage(throwable: Throwable, context: Context) : String
}