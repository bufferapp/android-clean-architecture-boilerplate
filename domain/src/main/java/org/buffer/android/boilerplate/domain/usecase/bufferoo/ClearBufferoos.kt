package org.buffer.android.boilerplate.domain.usecase.bufferoo

import io.reactivex.Completable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.domain.usecase.CompletableUseCase
import javax.inject.Inject

/**
 * Use case used for clearing the [List] of [Bufferoo] instances stored in the [BufferooRepository]
 */
open class ClearBufferoos @Inject constructor(private val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        CompletableUseCase<Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Completable {
        return bufferooRepository.clearBufferoos()
    }

}