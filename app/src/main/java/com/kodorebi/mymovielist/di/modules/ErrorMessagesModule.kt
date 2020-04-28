package com.kodorebi.mymovielist.di.modules

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import com.kodorebi.mymovielist.core.errormessages.IErrorMessages
import com.kodorebi.mymovielist.core.errormessages.ErrorMessages

object ErrorMessagesModule {
    fun build(): Kodein.Module {
        return Kodein.Module("ErrorMessagesModule") {
            bind<IErrorMessages>() with provider {
                ErrorMessages()
            }
        }
    }
}