package org.buffer.android.boilerplate.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.*
import io.reactivex.subscribers.DisposableSubscriber
import org.buffer.android.boilerplate.domain.interactor.browse.GetBufferoos
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.presentation.data.ResourceState
import org.buffer.android.boilerplate.presentation.mapper.BufferooMapper
import org.buffer.android.boilerplate.presentation.model.BufferooView
import org.buffer.android.boilerplate.presentation.test.factory.BufferooFactory
import org.buffer.android.boilerplate.presentation.test.factory.DataFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock

@RunWith(JUnit4::class)
class BrowseBufferoosViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var getBufferoos: GetBufferoos
    @Mock lateinit var bufferooMapper: BufferooMapper

    @Captor
    private lateinit var captor: KArgumentCaptor<DisposableSubscriber<List<Bufferoo>>>

    private lateinit var bufferoosViewModel: BrowseBufferoosViewModel

    @Before
    fun setUp() {
        captor = argumentCaptor<DisposableSubscriber<List<Bufferoo>>>()
        getBufferoos = mock()
        bufferooMapper = mock()
        bufferoosViewModel = BrowseBufferoosViewModel(getBufferoos, bufferooMapper)
    }

    @Test
    fun getBufferoosExecutesUseCase() {
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos, times(1)).execute(any(), anyOrNull())
    }

    //<editor-fold desc="Success">
    @Test
    fun getBufferoosReturnsSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)
        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.SUCCESS)
    }

    @Test
    fun getBufferoosReturnsDataOnSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)

        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.data == viewList)
    }

    @Test
    fun getBufferoosReturnsNoMessageOnSuccess() {
        val list = BufferooFactory.makeBufferooList(2)
        val viewList = BufferooFactory.makeBufferooViewList(2)

        stubBufferooMapperMapToView(viewList[0], list[0])
        stubBufferooMapperMapToView(viewList[1], list[1])

        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(list)

        assert(bufferoosViewModel.getBufferoos().value?.message == null)
    }
    //</editor-fold>

    //<editor-fold desc="Error">
    @Test
    fun getBufferoosReturnsError() {
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.ERROR)
    }

    @Test
    fun getBufferoosFailsAndContainsNoData() {
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }

    @Test
    fun getBufferoosFailsAndContainsMessage() {
        val errorMessage = DataFactory.randomUuid()
        bufferoosViewModel.getBufferoos()

        verify(getBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assert(bufferoosViewModel.getBufferoos().value?.message == errorMessage)
    }
    //</editor-fold>

    //<editor-fold desc="Loading">
    @Test
    fun getBufferoosReturnsLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.status == ResourceState.LOADING)
    }

    @Test
    fun getBufferoosContainsNoDataWhenLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }

    @Test
    fun getBufferoosContainsNoMessageWhenLoading() {
        bufferoosViewModel.getBufferoos()

        assert(bufferoosViewModel.getBufferoos().value?.data == null)
    }
    //</editor-fold>

    private fun stubBufferooMapperMapToView(bufferooView: BufferooView,
                                            bufferoo: Bufferoo) {
        whenever(bufferooMapper.mapToView(bufferoo))
                .thenReturn(bufferooView)
    }

}