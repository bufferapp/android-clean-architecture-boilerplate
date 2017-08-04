package org.buffer.android.boilerplate.domain.usecase.bufferoo

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.interactor.bufferoo.GetBufferoos
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.junit.Before
import org.junit.Test

class GetBufferoosTest {

    private lateinit var clearBufferoos: GetBufferoos

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockBufferooRepository: BufferooRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockBufferooRepository = mock()
        clearBufferoos = GetBufferoos(mockBufferooRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        clearBufferoos.buildUseCaseObservable(null)
        verify(mockBufferooRepository).clearBufferoos()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubBufferooRepositoryClearBufferoos(Completable.complete())
        val testObserver = clearBufferoos.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    private fun stubBufferooRepositoryClearBufferoos(completable: Completable) {
        whenever(mockBufferooRepository.clearBufferoos())
                .thenReturn(completable)
    }

}