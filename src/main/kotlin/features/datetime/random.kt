package features.datetime

import java.time.LocalDate
import kotlin.random.Random

fun randomNumbers() {
    val randomInt = Random.nextInt()
    println(randomInt)
    // 1873213158

    // Random Long 0..100
    val randomLong = Random.nextLong(until = 100)
    println(randomLong)
    // 47

    val randomDouble = Random.nextDouble(from = 30.5, until = 60.5)
    println(randomDouble)
    // 30.763336517810497
}

fun customRandomLocalDate() {
    repeat(10) {
        val randomLocalDate = Random.nextLocalDate(from = 1 January 2023, until = 1 January 2024)
        println(randomLocalDate)
    }
    // 2023-02-05
    // 2023-01-05
    // 2023-03-29
    // 2023-11-26
    // 2023-11-15
    // 2023-12-15
    // 2023-06-23
    // 2023-04-14
    // 2023-04-04
    // 2023-06-06
}

fun Random.Default.nextLocalDate(from: LocalDate = LocalDate.MIN, until: LocalDate = LocalDate.MAX): LocalDate {
    val fromEpochDay: Long = from.toEpochDay()
    val untilEpochDay: Long = until.toEpochDay()

    val randomLong = nextLong(fromEpochDay, untilEpochDay)
    return LocalDate.ofEpochDay(randomLong)
}


fun main() {
    randomNumbers()
    customRandomLocalDate()
}
