package features.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.toJavaDuration

fun additionForLocalDateTime() {
    val dateTime = 1 January 2024 at 09.00

    val resultDateTime = dateTime + 20.days

    println(resultDateTime)
    // 2024-01-21T09:00
}

operator fun LocalDateTime.plus(duration: kotlin.time.Duration): LocalDateTime {
    return this.plus(duration.toJavaDuration())
}

operator fun LocalDate.plus(duration: kotlin.time.Duration): LocalDateTime {
    return this.atStartOfDay().plus(duration.toJavaDuration())
}

fun subtractionForLocalDateTime() {
    val date = 1 January 2024

    val resultDateTime = date + 20.days - 10.hours - 10.nanoseconds

    println(resultDateTime)
    // 2024-01-20T22:59:59.999999990
}

operator fun LocalDateTime.minus(duration: kotlin.time.Duration): LocalDateTime {
    return this.minus(duration.toJavaDuration())
}

operator fun LocalDate.minus(duration: kotlin.time.Duration): LocalDateTime {
    return this.atStartOfDay().minus(duration.toJavaDuration())
}

fun main() {
    additionForLocalDateTime()

    subtractionForLocalDateTime()
}
