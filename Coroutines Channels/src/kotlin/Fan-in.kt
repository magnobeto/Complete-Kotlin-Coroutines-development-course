import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, 200L, "message1") }
        launch { sendString(channel, 500L, "message2") }
        repeat(6) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }
}

suspend fun sendString(channel: SendChannel<String>, time: Long, message: String) {
    while (true) {
        delay(time)
        channel.send(message)
    }
}