package com.kodorebi.mymovielist.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import com.kodorebi.mymovielist.di.KodeinBuilder

class App : Application() {
    companion object {
        lateinit var kodein : Kodein
            private set
    }


    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        kodein = KodeinBuilder.build(this)
    }
}