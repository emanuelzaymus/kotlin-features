@file:Suppress("FunctionName")

package features.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

fun creationOfLocalDate() {
    val date1 = LocalDate.of(2023, 8, 1)
    println(date1)
    // 2023-08-01

    val date2 = LocalDate.of(2023, 1, 8)
    println(date2)
    // 2023-01-08
}

fun customCreationLocalDate() {
    val date1 = 1 August 2023
    println(date1)
    // 2023-08-01

    val date2 = 8 January 2023
    println(date2)
    // 2023-01-08
}

infix fun Int.January(year: Int): LocalDate = LocalDate.of(year, Month.JANUARY, this)
infix fun Int.August(year: Int): LocalDate = LocalDate.of(year, Month.AUGUST, this)

fun creationOfLocalDateTime() {
    val dateTime = LocalDateTime.of(2023, 8, 31, 12, 30)
    println(dateTime)
    // 2023-08-31T12:30
}

fun customCreationLocalDateTime() {
    val dateTime = 31 August 2023 at 12.30
    println(dateTime)
    // 2023-08-31T12:30
}

infix fun LocalDate.at(time: Double): LocalDateTime {
    val hour = time.toInt() // Truncate decimals
    val minute = ((time - hour) * 100).roundToInt() // Round properly double to integer

    return this.atTime(hour, minute)
}

fun customCreationLocalDateTimeWithSeconds() {
    val dateTime = 31 August 2023 at 12.30 and 20.seconds
    println(dateTime)
    // 2023-08-31T12:30:20
}

infix fun LocalDateTime.and(duration: kotlin.time.Duration): LocalDateTime {
    return this.plus(duration.toJavaDuration())
}


fun main() {
    creationOfLocalDate()
    customCreationLocalDate()

    creationOfLocalDateTime()
    customCreationLocalDateTime()

    customCreationLocalDateTimeWithSeconds()
}
