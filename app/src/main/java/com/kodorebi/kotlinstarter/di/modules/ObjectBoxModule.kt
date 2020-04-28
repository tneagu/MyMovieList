package com.kodorebi.kotlinstarter.di.modules

import android.content.Context
import io.objectbox.BoxStore
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import com.kodorebi.kotlinstarter.db.domain.Db
import com.kodorebi.kotlinstarter.db.objectbox.ObjectBoxDb
import com.kodorebi.kotlinstarter.db.objectbox.models.MyObjectBox

object ObjectBoxModule {
    fun build(context: Context) : Kodein.Module {
        return Kodein.Module("ObjectBoxModule") {
            bind<BoxStore>() with singleton {
                MyObjectBox.builder()
                    .androidContext(context)
                    .build()
            }

            bind<Db>() with singleton {
                ObjectBoxDb(instance())
            }

        }
    }
}