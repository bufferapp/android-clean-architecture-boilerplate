package org.buffer.android.boilerplate.presentation.browse

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.buffer.android.boilerplate.domain.interactor.bufferoo.GetBufferoos
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.presentation.mapper.BufferooMapper
import org.buffer.android.boilerplate.presentation.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BrowseBufferoosPresenterTest {

    private lateinit var mockBrowseBufferoosView: BrowseBufferoosContract.View
    private lateinit var mockGetBufferoos: GetBufferoos
    private lateinit var mockBufferooMapper: BufferooMapper

    private lateinit var browseBufferoosPresenter: BrowseBufferoosPresenter

    @Before
    fun setup() {
        mockBrowseBufferoosView = mock()
        mockGetBufferoos = mock()
        mockBufferooMapper = mock()
        browseBufferoosPresenter = BrowseBufferoosPresenter(mockBrowseBufferoosView,
                mockGetBufferoos, mockBufferooMapper)
    }

    //<editor-fold desc="Retrieve Bufferoos">
    @Test
    fun retrieveBufferoosHidesErrorState() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideErrorState()
    }

    @Test
    fun retrieveBufferoosHidesEmptyState() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideEmptyState()
    }

    @Test
    fun retrieveBufferoosShowsBufferoos() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        stubGetBufferoos(Single.just(bufferoos))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).showBufferoos(
                bufferoos.map { mockBufferooMapper.mapToView(it) })
    }

    @Test
    fun retrieveBufferoosShowsEmptyState() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(0)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).showEmptyState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoos() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(0)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideBufferoos()
    }

    @Test
    fun retrieveBufferoosShowsErrorState() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideErrorState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoosWhenErrorThrown() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideErrorState()
    }

    @Test
    fun retrieveBufferoosHidesEmptyStateWhenErrorThrown() {
        stubGetBufferoos(Single.just(BufferooFactory.makeBufferooList(2)))

        browseBufferoosPresenter.retrieveBufferoos()
        verify(mockBrowseBufferoosView).hideErrorState()
    }
    //</editor-fold>

    private fun stubGetBufferoos(single: Single<List<Bufferoo>>) {
        whenever(mockGetBufferoos.buildUseCaseObservable(null))
                .thenReturn(single)
    }

}