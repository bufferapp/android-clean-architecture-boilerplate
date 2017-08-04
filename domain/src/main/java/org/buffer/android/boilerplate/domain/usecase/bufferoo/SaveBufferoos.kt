package org.buffer.android.boilerplate.domain.usecase.bufferoo

import io.reactivex.Completable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.domain.usecase.CompletableUseCase
import javax.inject.Inject

/**
 * Use case used for saving a [List] of [Bufferoo] instances using the [BufferooRepository]
 */
open class SaveBufferoos @Inject constructor(private val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        CompletableUseCase<SaveBufferoos.Params>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: SaveBufferoos.Params): Completable {
        return bufferooRepository.saveBufferoos(params.bufferoos)
    }

    class Params private constructor(val bufferoos: List<Bufferoo>) {
        companion object {
            fun forBufferoos(bufferoos: List<Bufferoo>): SaveBufferoos.Params {
                return SaveBufferoos.Params(bufferoos)
            }
        }
    }

}