package features.stdlib

import kotlin.time.Duration
import kotlin.time.measureTime

fun main() {

    val measureTime: Duration = measureTime {
        println(2)
    }

    measureTime.inWholeMilliseconds
    measureTime.inWholeSeconds
//    measureTime.toComponents { days, hours, minutes, seconds, nanoseconds -> }

    // ---------

    // example of some interesting kotlin stdlib functions

    check(true) { "" }
    checkNotNull("string") {}
    require(true) { "" }
    requireNotNull("string") {}
    error("This is an error message")

//    Delegates.vetoable(0)
//    Delegates.observable()

//    repeat(5) { println("Hello") }

    // runCatching {}
}
