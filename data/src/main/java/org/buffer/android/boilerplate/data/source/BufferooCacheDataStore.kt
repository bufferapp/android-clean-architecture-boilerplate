package org.buffer.android.boilerplate.data.source

import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooCache
import org.buffer.android.boilerplate.data.repository.BufferooDataStore
import javax.inject.Inject

/**
 * Created by joebirch on 03/08/2017.
 */
open class BufferooCacheDataStore @Inject constructor(private val bufferooCache: BufferooCache) :
        BufferooDataStore {

    override fun clearBufferoos(): Completable {
        return bufferooCache.clearBufferoos()
    }

    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        return bufferooCache.saveBufferoos(bufferoos)
                .doOnComplete {
                    bufferooCache.setLastCacheTime(System.currentTimeMillis())
                }
    }

    override fun getBufferoos(): Single<List<BufferooEntity>> {
        return bufferooCache.getBufferoos()
    }

}