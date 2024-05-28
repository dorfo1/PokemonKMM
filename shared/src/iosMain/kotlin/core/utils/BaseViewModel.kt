package core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

actual abstract class BaseViewModel<STATE, EVENT> {
    protected actual abstract val initialState: STATE

    protected actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow<STATE>(initialState)
    actual val state: StateFlow<STATE> get() = _state.asStateFlow()

    private val _event = MutableSharedFlow<EVENT>()
    actual val event: SharedFlow<EVENT> get() = _event.asSharedFlow()

    protected actual fun sendEvent(event: EVENT): Unit {
        scope.launch { _event.emit(event) }
    }

    protected actual fun updateState(block: (state: STATE) -> STATE) {
        scope.launch { _state.update { block.invoke(it) } }
    }


    fun onClear() = scope.cancel()
}