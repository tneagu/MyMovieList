package com.kodorebi.mymovielist.di.modules

import com.kodorebi.mymovielist.core.models.Session
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by TNE17909 on 4/28/2020.
 * Copyright © 2019 OpenGroupe. All rights reserved.
 */
object SessionModuleBuilder {
    fun build(): Kodein.Module {
        return Kodein.Module("SessionModule") {
            bind<Session>() with singleton { Session() }
        }
    }
}