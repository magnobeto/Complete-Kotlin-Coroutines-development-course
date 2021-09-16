import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
//        tryCatch()
//        catch()
        onCompletion()
    }
}

suspend fun onCompletion() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .onCompletion { e ->
            if(e != null)
                println("Flow completed with exception $e")
            else
                println("Flow completed successfully")
        }
        .catch { e -> println("Caught exception $e") }
        .collect { println(it) }
}

suspend fun catch() {
    (1..3).asFlow()
        .onEach { check(it != 2) }
        .catch { e -> println("Caught exception $e") }
        .collect { println(it) }
}

suspend fun tryCatch() {
    try {
        (1..3).asFlow()
            .onEach { check(it != 2) }
            .collect { println(it) }
    } catch (e: Exception) {
        println("Caught exception $e")
    }
}