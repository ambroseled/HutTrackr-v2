package com.seng440.ajl190.huttrackr

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.seng440.ajl190.huttrackr.data.DocDatabase
import com.seng440.ajl190.huttrackr.data.network.*
import com.seng440.ajl190.huttrackr.data.repository.*
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutViewModelFactory
import com.seng440.ajl190.huttrackr.viewmodel.factory.HutsListViewModelFactory
import com.seng440.ajl190.huttrackr.viewmodel.factory.TrackViewModelFactory
import com.seng440.ajl190.huttrackr.viewmodel.factory.TracksListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class DocApplication: Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@DocApplication))

        bind() from singleton { DocDatabase(instance()) }
        bind() from singleton { instance<DocDatabase>().hutDao() }
        bind() from singleton { instance<DocDatabase>().trackDao() }
        bind() from singleton { instance<DocDatabase>().wishHutItemDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { DocApiService(instance()) }
        bind<DocApiDataSource>() with singleton { DocApiDataSourceImpl(instance()) }

        bind<HutRepository>() with singleton { HutRepositoryImpl(instance(), instance()) }
        bind<WishHutItemRepository>() with singleton { WishHutItemRepositoryImpl(instance()) }

        bind() from provider { HutsListViewModelFactory(instance(), instance()) }
        bind() from provider { HutViewModelFactory(instance()) }

        bind<TrackRepository>() with singleton { TrackRepositoryImpl(instance(), instance()) }
        bind() from provider { TracksListViewModelFactory(instance()) }
        bind() from provider { TrackViewModelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}