package org.buffer.android.boilerplate.domain.usecase.bufferoo

import io.reactivex.Single
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import org.buffer.android.boilerplate.domain.usecase.SingleUseCase
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Bufferoo] instances from the [BufferooRepository]
 */
open class GetBufferoos @Inject constructor(private val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Bufferoo>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void): Single<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }


}