package core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

expect abstract class BaseViewModel<STATE, EVENT>() {
    protected abstract val initialState: STATE
    protected val scope: CoroutineScope

    val state: StateFlow<STATE>

    val event: SharedFlow<EVENT>

    protected fun sendEvent(event: EVENT)

    protected fun updateState(block: (state: STATE) -> STATE)
}