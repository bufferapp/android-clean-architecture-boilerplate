package org.buffer.android.boilerplate.presentation

/**
 * Interface class to act as a base for any class that is to take the role of the BaseView in the Model-
 * BaseView-Presenter pattern.
 */
interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}