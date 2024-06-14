package features.stdlib

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

fun systemNanoTime() {
    val start: Long = System.nanoTime()

    Thread.sleep(1000)

    val end: Long = System.nanoTime()

    val milliseconds = (end - start) / 1_000_000
    println("Time taken: $milliseconds ms")
    // Time taken: 1005 ms
}

fun measureTime() {
    var duration: Duration = measureTime {
        Thread.sleep(1000)
    }

    println("Time taken: $duration")
    // Time taken: 1.001017125s


    duration = measureTime {
        Thread.sleep(200)
    }

    println("Time taken: $duration")
    // Time taken: 206.310084ms
}

fun measureTimeInWhole() {
    val duration = measureTime {
        Thread.sleep(2500)
    }

    println("Time taken: ${duration.inWholeSeconds} seconds")
    // Time taken: 2 seconds

    println("Time taken: ${duration.inWholeMilliseconds} milliseconds")
    // Time taken: 2505 milliseconds

    println("Time taken: ${duration.inWholeMicroseconds} microseconds")
    // Time taken: 2505647 microseconds
}

fun measureTimedValue() {
    var answer: Int

    val duration = measureTime {
        answer = computeAnswer()
    }

    println("The answer is $answer. Computation took $duration")
    // The answer is 42. Computation took 1.006917375s


    val (answer2, duration2) = measureTimedValue {
        computeAnswer()
    }

    println("The answer is $answer2. Computation took $duration2")
    // The answer is 42. Computation took 1.005434167s
}

fun toComponents() {
    val duration = 2.days + 10.hours + 4.5.minutes - (50.seconds / 2) - 601.88.nanoseconds

    duration.toComponents { days, hours, minutes, seconds, nanoseconds ->
        println("Duration: $days days, $hours hours, $minutes minutes, $seconds seconds, $nanoseconds nanoseconds")
        // Duration: 2 days, 10 hours, 4 minutes, 4 seconds, 999999398 nanoseconds
    }
}

fun main() {
    systemNanoTime()
    measureTime()
    measureTimeInWhole()
    measureTimedValue()
    toComponents()
}

private fun computeAnswer(): Int {
    Thread.sleep(1000)
    return 42
}
