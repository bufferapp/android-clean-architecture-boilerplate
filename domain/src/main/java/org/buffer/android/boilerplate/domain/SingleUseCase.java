package org.buffer.android.boilerplate.domain;

import org.buffer.android.boilerplate.domain.executor.PostExecutionThread;
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link Disposable}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class SingleUseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Disposable subscription = Disposables.empty();

    protected SingleUseCase(ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link SingleUseCase}.
     */
    protected abstract Single<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     */
    @SuppressWarnings("unchecked")
    public Single<T> execute(Params params) {
        return this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
    }

    /**
     * Unsubscribes from current {@link Disposable}.
     */
    public void unsubscribe() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}