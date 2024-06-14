package features.stdlib

import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

var a = -10
var b = 20

var value: String? = null

fun require() {
    if (a !in 1..<100) {
        throw IllegalArgumentException("Number A $a must be greater or equal 1 and less than 100")
    }

    require(a >= 0) { "Number A $a cannot be negative" }
    // java.lang.IllegalArgumentException: Number A -10 cannot be negative


    if (value == null) {
        throw IllegalArgumentException("A cannot be less than B")
    }

    value ?: throw IllegalArgumentException("String cannot be null")

    val nonNullValue = requireNotNull(value) { "String cannot be null" }
    // java.lang.IllegalArgumentException: String cannot be null

    println(nonNullValue)
}

fun check() {
    val duration = measureTime {
        computeValue()
    }

    if (duration > 2.5.seconds) {
        throw IllegalStateException("Process took more than 2.5 seconds")
    }

    check(duration <= 2.5.seconds) { "Process took more than 2.5 seconds" }
    // java.lang.IllegalStateException: Process took more than 2.5 seconds


    val nonNullValue = checkNotNull(computeValue()) { "Computed value cannot be null" }
    // java.lang.IllegalStateException: Computed value cannot be null
    println(nonNullValue)

    val value = computeValue() ?: throw IllegalStateException("Computed value cannot be null")
    println(value)

    computeValue() ?: error("This is an error message")
}


fun main() {
    require()
    check()

    // ---------

    // example of some interesting kotlin stdlib functions

//    Objects.requireNonNullElseGet("string") { "default" }


//    "asdf".


//    check(true) { "" }
//    checkNotNull("string") {}
//    require(true) { "" }
//    requireNotNull("string") {}
//    error("This is an error message")
//    Delegates.vetoable(0)
//    Delegates.observable()

//    repeat(5) { println("Hello") }

    // runCatching {}

//    @JvmField
//    @JvmStatic
//        @JvmOverloads
//        @JvmName
//        @Throws
}

fun computeValue(): Int? {
    return null
}
