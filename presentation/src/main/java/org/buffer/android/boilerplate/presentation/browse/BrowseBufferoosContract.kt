package org.buffer.android.boilerplate.presentation.browse

import org.buffer.android.boilerplate.presentation.BasePresenter
import org.buffer.android.boilerplate.presentation.BaseView
import org.buffer.android.boilerplate.presentation.model.BufferooView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseBufferoosContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showBufferoos(bufferoos: List<BufferooView>)

        fun hideBufferoos()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveBufferoos()

    }

}