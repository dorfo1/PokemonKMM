package core.extensions

import core.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun <DATA, ERROR> Flow<DataResult<DATA, ERROR>>.onSuccess(
    action: suspend (DATA) -> Unit
): Flow<DataResult<DATA, ERROR>> = onEach {
    if (it is DataResult.Success) action.invoke(it.data)
}

fun <DATA, ERROR> Flow<DataResult<DATA, ERROR>>.onError(
    action: suspend (ERROR) -> Unit
): Flow<DataResult<DATA, ERROR>> = onEach {
    if (it is DataResult.Error) action.invoke(it.error)
}