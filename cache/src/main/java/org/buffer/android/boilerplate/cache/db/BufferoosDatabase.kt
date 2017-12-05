package org.buffer.android.boilerplate.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.buffer.android.boilerplate.cache.dao.CachedBufferooDao
import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import javax.inject.Inject

@Database(entities = arrayOf(CachedBufferoo::class), version = 1)
abstract class BufferoosDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedBufferooDao(): CachedBufferooDao

}