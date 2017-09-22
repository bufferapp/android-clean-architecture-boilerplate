package org.buffer.android.boilerplate.domain.interactor.browse

import io.reactivex.Flowable
import org.buffer.android.boilerplate.domain.executor.PostExecutionThread
import org.buffer.android.boilerplate.domain.executor.ThreadExecutor
import org.buffer.android.boilerplate.domain.interactor.FlowableUseCase
import org.buffer.android.boilerplate.domain.model.Bufferoo
import org.buffer.android.boilerplate.domain.repository.BufferooRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Bufferoo] instances from the [BufferooRepository]
 */
open class GetBufferoos @Inject constructor(val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        FlowableUseCase<List<Bufferoo>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Flowable<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }

}