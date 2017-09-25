package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface BufferooCache {

    /**
     * Clear all Bufferoos from the cache.
     */
    fun clearBufferoos(): Completable

    /**
     * Save a given list of Bufferoos to the cache.
     */
    fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache.
     */
    fun getBufferoos(): Flowable<List<BufferooEntity>>

    /**
     * Check whether there is a list of Bufferoos stored in the cache.
     *
     * @return true if the list is cached, otherwise false
     */
    fun isCached(): Single<Boolean>

    /**
     * Set a point in time at when the cache was last updated.
     *
     * @param lastCache the point in time at when the cache was last updated
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Check if the cache is expired.
     *
     * @return true if the cache is expired, otherwise false
     */
    fun isExpired(): Boolean

}