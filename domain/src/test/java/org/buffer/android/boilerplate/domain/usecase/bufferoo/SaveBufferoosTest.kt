package org.buffer.android.boilerplate.domain.usecase.bufferoo

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.domain.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test

class SaveBufferoosTest {

    private lateinit var saveBufferoos: SaveBufferoos

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockBufferooRepository: BufferooRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockBufferooRepository = mock()
        saveBufferoos = SaveBufferoos(mockBufferooRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        saveBufferoos.buildUseCaseObservable(SaveBufferoos.Params.forBufferoos(bufferoos))
        verify(mockBufferooRepository).saveBufferoos(bufferoos)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubBufferooRepositorySaveBufferoos(Completable.complete())
        val testObserver = saveBufferoos.buildUseCaseObservable(SaveBufferoos.Params.forBufferoos(
                BufferooFactory.makeBufferooList(2))).test()
        testObserver.assertComplete()
    }

    private fun stubBufferooRepositorySaveBufferoos(completable: Completable) {
        whenever(mockBufferooRepository.saveBufferoos(any()))
                .thenReturn(completable)
    }

}