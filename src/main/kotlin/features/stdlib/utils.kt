package features.stdlib

import java.io.FileReader
import java.io.FileWriter
import kotlin.properties.Delegates
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
        throw IllegalArgumentException("String cannot be null")
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

    computeValue() ?: error("Computed value cannot be null")
}

fun repeat() {
    for (i in 1..5) {
        print("Hello ")
    }
    // Hello Hello Hello Hello Hello

    repeat(5) {
        print("Hello ")
    }
    // Hello Hello Hello Hello Hello
}

fun use() {
    FileWriter("output.txt").use {
        it.write("Hello, world!")
    }

    val text = FileReader("output.txt").use {
        it.readText()
    }

    println(text)
    // Hello, world!
}

var observableString: String by Delegates.observable("Initial value") { property, oldValue, newValue ->
    println("Property ${property.name} changed from '$oldValue' to '$newValue'")
}

fun observable() {
    println(observableString)
    // Initial value

    observableString = "New value"
    // Property observableString changed from 'Initial value' to 'New value'

    println(observableString)
    // New value
}

var vetoValue: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
    if (newValue > oldValue) {
        println("Property ${property.name} CAN be changed from $oldValue to $newValue")
        true
    } else {
        println("Property ${property.name} CANNOT be changed from $oldValue to $newValue")
        false
    }
}

fun veto() {
    println(vetoValue)
    // 0

    vetoValue = 10
    // Property vetoValue CAN be changed from 0 to 10

    println(vetoValue)
    // 10

    vetoValue = 5
    // Property vetoValue CANNOT be changed from 10 to 5

    println(vetoValue)
    // 10
}

fun main() {
    require()
    check()
    repeat()
    use()
    observable()
    veto()

//    runCatching { computeValue() }

//    @JvmField
//    @JvmStatic
//    @JvmOverloads
//    @JvmName
//    @Throws
}

fun computeValue(): Int? {
    return null
}
