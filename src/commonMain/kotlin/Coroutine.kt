import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun coroutine(crossinline block: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Dispatchers.Default).launch { block() }