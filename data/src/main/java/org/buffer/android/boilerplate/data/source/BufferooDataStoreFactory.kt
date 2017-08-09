package org.buffer.android.boilerplate.data.source

import org.buffer.android.boilerplate.data.repository.BufferooCache
import org.buffer.android.boilerplate.data.repository.BufferooDataStore
import org.buffer.android.boilerplate.data.source.BufferooCacheDataStore
import org.buffer.android.boilerplate.data.source.BufferooRemoteDataStore
import javax.inject.Inject

/**
 * Created by joebirch on 03/08/2017.
 */
class BufferooDataStoreFactory @Inject constructor(
        private val bufferooCache: BufferooCache,
        private val bufferooCacheDataStore: BufferooCacheDataStore,
        private val bufferooRemoteDataStore: BufferooRemoteDataStore) {

    fun retrieveDataStore(): BufferooDataStore {
        if (bufferooCache.isCached() && !bufferooCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    fun retrieveCacheDataStore(): BufferooDataStore {
        return bufferooCacheDataStore
    }

    fun retrieveRemoteDataStore(): BufferooDataStore {
        return bufferooRemoteDataStore
    }

}