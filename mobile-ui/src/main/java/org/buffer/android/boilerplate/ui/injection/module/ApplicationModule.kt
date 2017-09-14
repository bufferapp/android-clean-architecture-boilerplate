package org.buffer.android.boilerplate.ui.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.cache.BufferooCacheImpl
import org.buffer.android.boilerplate.cache.PreferencesHelper
import org.buffer.android.boilerplate.cache.db.BufferoosDatabase
import org.buffer.android.boilerplate.cache.mapper.BufferooEntityMapper
import org.buffer.android.boilerplate.data.BufferooDataRepository
import org.buffer.android.boilerplate.data.executor.JobExecutor
import org.buffer.android.boilerplate.data.mapper.BufferooMapper
import org.buffer.android.boilerplate.data.repository.BufferooCache
import org.buffer.android.boilerplate.data.repository.BufferooRemote
import org.buffer.android.boilerplate.data.source.BufferooDataStoreFactory
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.remote.BufferooRemoteImpl
import org.buffer.android.boilerplate.remote.BufferooService
import org.buffer.android.boilerplate.remote.BufferooServiceFactory
import org.buffer.android.boilerplate.ui.BuildConfig
import org.buffer.android.boilerplate.ui.UiThread
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @PerApplication
    internal fun provideBufferooRepository(factory: BufferooDataStoreFactory,
                                           mapper: BufferooMapper): BufferooRepository {
        return BufferooDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(database: BufferoosDatabase,
                                      entityMapper: BufferooEntityMapper,
                                      helper: PreferencesHelper): BufferooCache {
        return BufferooCacheImpl(database, entityMapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(service: BufferooService,
                                       factory: org.buffer.android.boilerplate.remote.mapper.BufferooEntityMapper): BufferooRemote {
        return BufferooRemoteImpl(service, factory)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideBufferooService(): BufferooService {
        return BufferooServiceFactory.makeBuffeoorService(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun provideBufferoosDatabase(application: Application): BufferoosDatabase {
        return Room.databaseBuilder(application.applicationContext,
                BufferoosDatabase::class.java, "bufferoos.db")
                .allowMainThreadQueries()
                .build()
    }

}
