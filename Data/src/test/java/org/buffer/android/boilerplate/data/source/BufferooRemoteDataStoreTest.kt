package org.buffer.android.boilerplate.data.source

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.data.repository.BufferooRemote
import org.buffer.android.boilerplate.data.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BufferooRemoteDataStoreTest {

    private lateinit var bufferooRemoteDataStore: BufferooRemoteDataStore

    private lateinit var bufferooRemote: BufferooRemote

    @Before
    fun setUp() {
        bufferooRemote = mock()
        bufferooRemoteDataStore = BufferooRemoteDataStore(bufferooRemote)
    }

    //<editor-fold desc="Clear Bufferoos">
    @Test(expected = UnsupportedOperationException::class)
    fun clearBufferoosThrowsException() {
        bufferooRemoteDataStore.clearBufferoos().test()
    }
    //</editor-fold>

    //<editor-fold desc="Save Bufferoos">
    @Test(expected = UnsupportedOperationException::class)
    fun saveBufferoosThrowsException() {
        bufferooRemoteDataStore.saveBufferoos(BufferooFactory.makeBufferooEntityList(2)).test()
    }
    //</editor-fold>

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooCacheGetBufferoos(Single.just(BufferooFactory.makeBufferooEntityList(2)))
        val testObserver = bufferooRemote.getBufferoos().test()
        testObserver.assertComplete()
    }
    //</editor-fold>

    //<editor-fold desc="Stub helper methods">
    private fun stubBufferooCacheGetBufferoos(single: Single<List<BufferooEntity>>) {
        whenever(bufferooRemote.getBufferoos())
                .thenReturn(single)
    }
    //</editor-fold>

}