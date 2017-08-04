package org.buffer.android.boilerplate.presentation.browse

import io.reactivex.disposables.Disposable
import org.buffer.android.boilerplate.domain.interactor.BaseSingleObserver
import org.buffer.android.boilerplate.domain.interactor.bufferoo.GetBufferoos
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.presentation.mapper.BufferooMapper
import javax.inject.Inject

class BrowseBufferoosPresenter @Inject constructor(val browseView: BrowseBufferoosContract.View,
                                                   val getBufferoosUseCase: GetBufferoos,
                                                   val bufferooMapper: BufferooMapper):
        BrowseBufferoosContract.Presenter {

    private var getBufferoosDisposable: Disposable? = null

    init {
        browseView.setPresenter(this)
    }

    override fun start() {
        retrieveBufferoos()
    }

    override fun stop() {
        if (getBufferoosDisposable != null) {
            (getBufferoosDisposable as Disposable).dispose()
        }
    }

    override fun retrieveBufferoos() {
        getBufferoosUseCase.buildUseCaseObservable(null)
                .subscribe(BufferooSubscriber())
    }

    private inner class BufferooSubscriber: BaseSingleObserver<List<Bufferoo>>() {

        override fun onSubscribe(d: Disposable) {
            getBufferoosDisposable = d
        }

        override fun onSuccess(t: List<Bufferoo>) {
            browseView.hideErrorState()
            if (t.isNotEmpty()) {
                browseView.hideEmptyState()
                browseView.showBufferoos(t.map { bufferooMapper.mapToView(it) })
            } else {
                browseView.hideBufferoos()
                browseView.showEmptyState()
            }

        }

        override fun onError(exception: Throwable) {
            browseView.hideBufferoos()
            browseView.hideEmptyState()
            browseView.showErrorState()
        }

    }

}