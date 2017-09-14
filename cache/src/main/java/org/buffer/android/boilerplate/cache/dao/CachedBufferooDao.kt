package org.buffer.android.boilerplate.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import org.buffer.android.boilerplate.cache.db.constants.BufferooConstants
import org.buffer.android.boilerplate.cache.model.CachedBufferoo

@Dao
abstract class CachedBufferooDao {

    @Query(BufferooConstants.QUERY_BUFFEROOS)
    abstract fun getBufferoos(): List<CachedBufferoo>

    @Query(BufferooConstants.DELETE_ALL_BUFFEROOS)
    abstract fun clearBufferoos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBufferoo(cachedBufferoo: CachedBufferoo)

}