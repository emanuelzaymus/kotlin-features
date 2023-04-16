package features.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours

fun dateInDateRange() {
    val from = 5 January 2023
    val to = 10 January 2023
    val dateRange = from..to

    println(7 January 2023 in dateRange)
    // true
    println(11 January 2023 in dateRange)
    // false
}

fun dateTimeInDate() {
    val date = 5 January 2023

    val dateTime = date + 5.hours

    val contains = dateTime in date
    println(contains)
    // true
    val notContains = dateTime !in date
    println(notContains)
    // false
}

operator fun LocalDate.contains(dateTime: LocalDateTime): Boolean {
    val from = this.atStartOfDay()
    val until = this + 1.days

    return from <= dateTime && dateTime < until
}


fun main() {
    dateInDateRange()
    dateTimeInDate()
}
