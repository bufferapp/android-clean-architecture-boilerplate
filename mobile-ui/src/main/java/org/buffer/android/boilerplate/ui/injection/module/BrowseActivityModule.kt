package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.Provides
import org.buffer.android.boilerplate.domain.interactor.browse.GetBufferoos
import org.buffer.android.boilerplate.presentation.browse.BrowseBufferoosViewModelFactory
import org.buffer.android.boilerplate.presentation.mapper.BufferooMapper


/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @Provides
    fun provideBrowseBufferoosViewModelFactory(getBufferoos: GetBufferoos,
                                               bufferooMapper: BufferooMapper):
            BrowseBufferoosViewModelFactory {
        return BrowseBufferoosViewModelFactory(getBufferoos, bufferooMapper)
    }

}
