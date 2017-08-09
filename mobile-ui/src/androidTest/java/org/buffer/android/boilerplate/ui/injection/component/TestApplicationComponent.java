package org.buffer.android.boilerplate.ui.injection.component;

import android.app.Application;

import org.buffer.android.boilerplate.domain.executor.PostExecutionThread;
import org.buffer.android.boilerplate.domain.repository.BufferooRepository;
import org.buffer.android.boilerplate.ui.injection.ApplicationComponent;
import org.buffer.android.boilerplate.ui.injection.module.ActivityBindingModule;
import org.buffer.android.boilerplate.ui.injection.scopes.PerApplication;
import org.buffer.android.boilerplate.ui.TestApplication;
import org.buffer.android.boilerplate.ui.injection.module.TestApplicationModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        TestApplicationModule.class, ActivityBindingModule.class, AndroidSupportInjectionModule.class
})
@PerApplication
public interface TestApplicationComponent extends ApplicationComponent {

    BufferooRepository bufferooRepository();

    PostExecutionThread postExecutionThread();

    void inject(TestApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestApplicationComponent.Builder application(Application application);

        TestApplicationComponent build();
    }

}