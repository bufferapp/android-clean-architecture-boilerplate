package org.buffer.android.boilerplate.presentation.browse

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.buffer.android.boilerplate.domain.interactor.browse.GetBufferoos
import org.buffer.android.boilerplate.presentation.mapper.BufferooMapper

open class BrowseBufferoosViewModelFactory(
        private val getBufferoos: GetBufferoos,
        private val bufferooMapper: BufferooMapper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseBufferoosViewModel::class.java)) {
            return BrowseBufferoosViewModel(getBufferoos, bufferooMapper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}