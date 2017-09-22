package org.buffer.android.boilerplate.remote

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.buffer.android.boilerplate.data.model.BufferooEntity
import org.buffer.android.boilerplate.remote.mapper.BufferooEntityMapper
import org.buffer.android.boilerplate.remote.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BufferooRemoteImplTest {

    private lateinit var entityMapper: BufferooEntityMapper
    private lateinit var bufferooService: BufferooService

    private lateinit var bufferooRemoteImpl: BufferooRemoteImpl

    @Before
    fun setup() {
        entityMapper = mock()
        bufferooService = mock()
        bufferooRemoteImpl = BufferooRemoteImpl(bufferooService, entityMapper)
    }

    //<editor-fold desc="Get Bufferoos">
    @Test
    fun getBufferoosCompletes() {
        stubBufferooServiceGetBufferoos(Flowable.just(BufferooFactory.makeBufferooResponse()))
        val testObserver = bufferooRemoteImpl.getBufferoos().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBufferoosReturnsData() {
        val bufferooResponse = BufferooFactory.makeBufferooResponse()
        stubBufferooServiceGetBufferoos(Flowable.just(bufferooResponse))
        val bufferooEntities = mutableListOf<BufferooEntity>()
        bufferooResponse.team.forEach {
            bufferooEntities.add(entityMapper.mapFromRemote(it))
        }

        val testObserver = bufferooRemoteImpl.getBufferoos().test()
        testObserver.assertValue(bufferooEntities)
    }
    //</editor-fold>

    private fun stubBufferooServiceGetBufferoos(observable:
                                                Flowable<BufferooService.BufferooResponse>) {
        whenever(bufferooService.getBufferoos())
                .thenReturn(observable)
    }
}