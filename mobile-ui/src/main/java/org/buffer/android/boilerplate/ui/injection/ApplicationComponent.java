package org.buffer.android.boilerplate.ui.injection;

import android.app.Application;

import org.buffer.android.boilerplate.ui.BufferooApplication;
import org.buffer.android.boilerplate.ui.injection.module.ActivityBindingModule;
import org.buffer.android.boilerplate.ui.injection.module.ApplicationModule;
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@PerApplication
@Component(modules = {
        ActivityBindingModule.class,
        ApplicationModule.class,
        AndroidSupportInjectionModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        ApplicationComponent build();
    }

    void inject(BufferooApplication app);

}
