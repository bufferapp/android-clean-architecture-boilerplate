package org.buffer.android.boilerplate.presentation.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelUtil @Inject constructor() {

    fun <T : ViewModel> createFor(viewModel: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModel.javaClass)) {
                    return viewModel as T
                }
                throw IllegalArgumentException("unexpected viewModel class " + modelClass)
            }
        }
    }

}