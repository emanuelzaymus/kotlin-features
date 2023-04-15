package features.datetime

import java.time.LocalDate
import java.time.Period

fun forLoopInKotlin() {
    for (i in 1..10) {
        print("$i, ")
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
}

fun forLoopOverLocalDates() {
    val from = 29 January 2023
    val to = LocalDate.of(2023, 2, 5)

    for (date in from..to) {
        print("$date, ")
    }
    // 2023-01-29, 2023-01-30, 2023-01-31, 2023-02-01, 2023-02-02, 2023-02-03, 2023-02-04, 2023-02-05,
}

operator fun LocalDate.rangeTo(toDate: LocalDate): Iterable<LocalDate> {
    val daysBetween = Period.between(this, toDate).days

    return sequence {
        for (i in 0..daysBetween) {
            yield(this@rangeTo.plusDays(i.toLong()))
        }
    }.asIterable()
}

fun forLoopUntilOverInts() {
    for (i in 1 until 10) {
        print("$i, ")
    }
    // 1, 2, 3, 4, 5, 6, 7, 8, 9,
}

fun forLoopUntilOverLocalDates() {
    val from = 29 January 2023
    val to = LocalDate.of(2023, 2, 5)

    for (date in from until to) {
        print("$date, ")
    }
    // 2023-01-29, 2023-01-30, 2023-01-31, 2023-02-01, 2023-02-02, 2023-02-03, 2023-02-04,
}

infix fun LocalDate.until(toDate: LocalDate): Iterable<LocalDate> {
    return this..(toDate.minusDays(1))
}

fun forLoopDownToOverInts() {
    for (i in 10 downTo 1) {
        print("$i, ")
    }
    // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
}

fun forLoopDownToOverLocalDates() {
    val from = 29 January 2023
    val to = LocalDate.of(2023, 2, 5)

    for (date in to downTo from) {
        print("$date, ")
    }
    // 2023-02-05, 2023-02-04, 2023-02-03, 2023-02-02, 2023-02-01, 2023-01-31, 2023-01-30, 2023-01-29,
}

infix fun LocalDate.downTo(toDate: LocalDate): Iterable<LocalDate> {
    val daysBetween = Period.between(toDate, this).days

    return sequence {
        for (i in daysBetween downTo 0) {
            yield(toDate.plusDays(i.toLong()))
        }
    }.asIterable()
}

fun forLoopWithStepOverInts() {
    for (i in 1..10 step 2) {
        print("$i, ")
    }
    // 1, 3, 5, 7, 9,
}

fun forLoopWithStepOverLocalDates() {
    val from = 29 January 2023
    val to = LocalDate.of(2023, 2, 5)

    for (date in from..to step 2) {
        print("$date, ")
    }
    // 2023-01-29, 2023-01-31, 2023-02-02, 2023-02-04,
}

infix fun Iterable<LocalDate>.step(step: Int): Iterable<LocalDate> {
    return sequence {
        for ((index, date) in this@step.withIndex()) {
            if (index % step == 0) {
                yield(date)
            }
        }
    }.asIterable()
}


fun main() {
    forLoopInKotlin()
    println()
    forLoopOverLocalDates()
    println()

    forLoopUntilOverInts()
    println()
    forLoopUntilOverLocalDates()
    println()

    forLoopDownToOverInts()
    println()
    forLoopDownToOverLocalDates()
    println()

    forLoopWithStepOverInts()
    println()
    forLoopWithStepOverLocalDates()
    println()
}
