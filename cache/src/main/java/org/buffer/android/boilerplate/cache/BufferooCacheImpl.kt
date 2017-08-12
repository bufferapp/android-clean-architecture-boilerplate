package org.buffer.android.boilerplate.cache

import android.database.sqlite.SQLiteDatabase
import io.reactivex.Completable
import io.reactivex.Single
import org.buffer.android.boilerplate.cache.db.Db
import org.buffer.android.boilerplate.cache.db.DbOpenHelper
import org.buffer.android.boilerplate.cache.db.constants.BufferooConstants
import org.buffer.android.boilerplate.cache.db.mapper.BufferooMapper
import org.buffer.android.boilerplate.cache.mapper.BufferooEntityMapper
import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooCache
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Bufferoo instances. This class implements the
 * [BufferooCache] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class BufferooCacheImpl @Inject constructor(dbOpenHelper: DbOpenHelper,
                                            private val entityMapper: BufferooEntityMapper,
                                            private val mapper: BufferooMapper,
                                            private val preferencesHelper: PreferencesHelper):
        BufferooCache {

    private val EXPIRATION_TIME = (60 * 10 * 1000).toLong()

    private var database: SQLiteDatabase = dbOpenHelper.writableDatabase

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): SQLiteDatabase {
        return database
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    override fun clearBufferoos(): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                database.delete(Db.BufferooTable.TABLE_NAME, null, null)
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Save the given list of [BufferooEntity] instances to the database.
     */
    override fun saveBufferoos(bufferoos: List<BufferooEntity>): Completable {
        return Completable.defer {
            database.beginTransaction()
            try {
                bufferoos.forEach {
                    saveBufferoo(entityMapper.mapToCached(it))
                }
                database.setTransactionSuccessful()
            } finally {
                database.endTransaction()
            }
            Completable.complete()
        }
    }

    /**
     * Retrieve a list of [BufferooEntity] instances from the database.
     */
    override fun getBufferoos(): Single<List<BufferooEntity>> {
        return Single.defer<List<BufferooEntity>> {
            val updatesCursor = database.rawQuery(BufferooConstants.QUERY_GET_ALL_BUFFEROOS, null)
            val bufferoos = mutableListOf<BufferooEntity>()

            while (updatesCursor.moveToNext()) {
                val cachedBufferoo = mapper.parseCursor(updatesCursor)
                bufferoos.add(entityMapper.mapFromCached(cachedBufferoo))
            }

            updatesCursor.close()
            Single.just<List<BufferooEntity>>(bufferoos)
        }
    }

    /**
     * Helper method for saving a [CachedBufferoo] instance to the database.
     */
    private fun saveBufferoo(cachedBufferoo: CachedBufferoo) {
        database.insert(Db.BufferooTable.TABLE_NAME, null, mapper.toContentValues(cachedBufferoo))
    }

    /**
     * Checked whether there are instances of [CachedBufferoo] stored in the cache
     */
    override fun isCached(): Boolean {
        return database.rawQuery(BufferooConstants.QUERY_GET_ALL_BUFFEROOS, null).count > 0
    }

    /**
     * Set a point in time at when the cache was last updated
     */
    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Check whether the current cached data exceeds the defined [EXPIRATION_TIME] time
     */
    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

}