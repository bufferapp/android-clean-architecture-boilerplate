package org.buffer.android.boilerplate.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity

/**
 * Interface defining methods for the caching of Bufferroos. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface BufferooCache {

    /**
     * Clear all Bufferoos from the cache
     */
    fun clearBufferoos(): Completable

    /**
     * Save a given list of Bufferoos to the cache
     */
    fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable

    /**
     * Retrieve a list of Bufferoos, from the cache
     */
    fun getBufferoos(): Single<List<BufferooEntity>>

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun isCached(): Boolean

    /**
     * Checks if an element (User) exists in the cache.

     * @param userId The id used to look for inside the cache.
     * *
     * @return true if the element is cached, otherwise false.
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.

     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean

}