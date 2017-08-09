package org.buffer.android.boilerplate.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.buffer.android.boilerplate.ui.browse.BrowseActivity
import org.buffer.android.boilerplate.ui.injection.scopes.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindMainActivity(): BrowseActivity

}