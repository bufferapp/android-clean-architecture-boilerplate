package org.buffer.android.boilerplate.ui

import org.buffer.android.boilerplate.ui.mapper.BufferooMapper
import org.buffer.android.boilerplate.ui.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class BufferooMapperTest {

    private lateinit var bufferooMapper: BufferooMapper

    @Before
    fun setUp() {
        bufferooMapper = BufferooMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val bufferooView = BufferooFactory.makeBufferooView()
        val bufferooViewModel = bufferooMapper.mapToViewModel(bufferooView)

        assertEquals(bufferooView.name, bufferooViewModel.name)
        assertEquals(bufferooView.title, bufferooViewModel.title)
        assertEquals(bufferooView.avatar, bufferooViewModel.avatar)
    }

}