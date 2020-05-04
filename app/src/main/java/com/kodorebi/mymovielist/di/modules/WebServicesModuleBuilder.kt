package com.kodorebi.mymovielist.di.modules

import com.kodorebi.mymovielist.ws.firebase.FirebaseAuthentification
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import kotlin.reflect.KClass

object WebServicesModuleBuilder {
    fun build(): Kodein.Module {
        return Kodein.Module("WebServicesModule") {
            fun <T : Any> create(retrofit: Retrofit, serviceClass: KClass<T>) : T {
                return retrofit.create(serviceClass.java)
            }

            bind() from singleton { FirebaseAuthentification() }

        }
    }
}