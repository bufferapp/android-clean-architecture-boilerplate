package org.buffer.android.boilerplate.ui.injection.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.presentation.browse.BrowseBufferoosViewModel
import org.buffer.android.boilerplate.presentation.util.ViewModelUtil
import javax.inject.Singleton


/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @Singleton
    @Provides
    fun provideViewModel(viewModelUtil: ViewModelUtil, viewModel: BrowseBufferoosViewModel):
            ViewModelProvider.Factory {
        return viewModelUtil.createFor(viewModel)
    }

}
