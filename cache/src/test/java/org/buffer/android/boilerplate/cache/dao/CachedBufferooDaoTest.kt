package org.buffer.android.boilerplate.cache.dao

import android.arch.persistence.room.Room
import org.buffer.android.boilerplate.cache.db.BufferoosDatabase
import org.buffer.android.boilerplate.cache.test.factory.BufferooFactory
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
open class CachedBufferooDaoTest {

    private lateinit var bufferoosDatabase: BufferoosDatabase

    @Before
    fun initDb() {
        bufferoosDatabase = Room.inMemoryDatabaseBuilder(
                RuntimeEnvironment.application.baseContext,
                BufferoosDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        bufferoosDatabase.close()
    }

    @Test
    fun insertBufferoosSavesData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        bufferoosDatabase.cachedBufferooDao().insertBufferoo(cachedBufferoo)

        val bufferoos = bufferoosDatabase.cachedBufferooDao().getBufferoos()
        assert(bufferoos.isNotEmpty())
    }

    @Test
    fun getBufferoosRetrievesData() {
        val cachedBufferoos = BufferooFactory.makeCachedBufferooList(5)

        cachedBufferoos.forEach {
            bufferoosDatabase.cachedBufferooDao().insertBufferoo(it) }

        val retrievedBufferoos = bufferoosDatabase.cachedBufferooDao().getBufferoos()
        assert(retrievedBufferoos == cachedBufferoos.sortedWith(compareBy({ it.id }, { it.id })))
    }

}