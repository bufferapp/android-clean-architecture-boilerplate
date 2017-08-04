package org.buffer.android.boilerplate.cache.mapper

import org.buffer.android.boilerplate.cache.model.CachedBufferoo
import org.buffer.android.boilerplate.cache.test.factory.BufferooFactory
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class BufferooEntityMapperTest {

    private lateinit var bufferooEntityMapper: BufferooEntityMapper

    @Before
    fun setUp() {
        bufferooEntityMapper = BufferooEntityMapper()
    }

    @Test
    fun mapToCachedMapsData() {
        val bufferooEntity = BufferooFactory.makeBufferooEntity()
        val cachedBufferoo = bufferooEntityMapper.mapToCached(bufferooEntity)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    @Test
    fun mapFromCachedMapsData() {
        val cachedBufferoo = BufferooFactory.makeCachedBufferoo()
        val bufferooEntity = bufferooEntityMapper.mapFromCached(cachedBufferoo)

        assertBufferooDataEquality(bufferooEntity, cachedBufferoo)
    }

    private fun assertBufferooDataEquality(bufferooEntity: BufferooEntity,
                                           cachedBufferoo: CachedBufferoo) {
        assertEquals(bufferooEntity.name, cachedBufferoo.name)
        assertEquals(bufferooEntity.title, cachedBufferoo.title)
        assertEquals(bufferooEntity.avatar, cachedBufferoo.avatar)
    }

}