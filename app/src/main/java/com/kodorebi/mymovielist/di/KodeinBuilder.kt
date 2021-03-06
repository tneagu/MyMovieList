package com.kodorebi.mymovielist.di

import android.app.Application
import org.kodein.di.Kodein
import com.kodorebi.mymovielist.di.modules.*

object KodeinBuilder {
    fun build(app: Application): Kodein {
        return Kodein {
            import(ConfigModuleBuilder.build())
            import(RetrofitModuleBuilder.build(app))
            import(WebServicesModuleBuilder.build())
            import(ObjectBoxModule.build(app))
            import(SessionModuleBuilder.build())
            import(ErrorMessagesModule.build())
        }
    }
}