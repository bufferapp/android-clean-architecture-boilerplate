package org.buffer.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.buffer.android.boilerplate.data.repository.BufferooCache
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BufferooDataStoreFactoryTest {

    private lateinit var bufferooDataStoreFactory: BufferooDataStoreFactory

    private lateinit var bufferooCache: BufferooCache
    private lateinit var bufferooCacheDataStore: BufferooCacheDataStore
    private lateinit var bufferooRemoteDataStore: BufferooRemoteDataStore

    @Before
    fun setUp() {
        bufferooCache = mock()
        bufferooCacheDataStore = mock()
        bufferooRemoteDataStore = mock()
        bufferooDataStoreFactory = BufferooDataStoreFactory(bufferooCache,
                bufferooCacheDataStore, bufferooRemoteDataStore)
    }

    //<editor-fold desc="Retrieve Data Store">
    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubBufferooCacheIsCached(Single.just(false))
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore(false)
        assert(bufferooDataStore is BufferooRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenCacheExpiredReturnsRemoteDataStore() {
        stubBufferooCacheIsCached(Single.just(true))
        stubBufferooCacheIsExpired(true)
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore(true)
        assert(bufferooDataStore is BufferooRemoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubBufferooCacheIsCached(Single.just(true))
        stubBufferooCacheIsExpired(false)
        val bufferooDataStore = bufferooDataStoreFactory.retrieveDataStore(true)
        assert(bufferooDataStore is BufferooCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Remote Data Store">
    @Test
    fun retrieveRemoteDataStoreReturnsRemoteDataStore() {
        val bufferooDataStore = bufferooDataStoreFactory.retrieveRemoteDataStore()
        assert(bufferooDataStore is BufferooRemoteDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Retrieve Cache Data Store">
    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val bufferooDataStore = bufferooDataStoreFactory.retrieveCacheDataStore()
        assert(bufferooDataStore is BufferooCacheDataStore)
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheIsCached(single: Single<Boolean>) {
        whenever(bufferooCache.isCached())
                .thenReturn(single)
    }

    private fun stubBufferooCacheIsExpired(isExpired: Boolean) {
        whenever(bufferooCache.isExpired())
                .thenReturn(isExpired)
    }
    //</editor-fold>

}