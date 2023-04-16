package features.datetime

fun javaLocalDateComparison() {
    val firstDate = 1 August 2023
    val secondDate = 10 August 2023

    val isBefore = firstDate.isBefore(secondDate)
    println(isBefore) // true
    val isAfter = firstDate.isAfter(secondDate)
    println(isAfter) // false

    val isEqual = firstDate.isEqual(secondDate)
    println(isEqual) // false
    val isNotEqual = !firstDate.isEqual(secondDate)
    println(isNotEqual) // true

    val isBeforeOfEqual = !firstDate.isAfter(secondDate)
    println(isBeforeOfEqual) // true
    val isAfterOrEqual = !firstDate.isBefore(secondDate)
    println(isAfterOrEqual) // false
}

fun comparisonOfLocalDateInKotlin() {
    val firstDate = 1 August 2023
    val secondDate = 10 August 2023

    val isBefore = firstDate < secondDate
    println(isBefore) // true
    val isAfter = firstDate > secondDate
    println(isAfter) // false

    val isEqual = firstDate == secondDate
    println(isEqual) // false
    val isNotEqual = firstDate != secondDate
    println(isNotEqual) // true

    val isBeforeOfEqual = firstDate <= secondDate
    println(isBeforeOfEqual) // true
    val isAfterOrEqual = firstDate >= secondDate
    println(isAfterOrEqual) // false

    val isSame = firstDate === secondDate
    println(isSame) // false
    val isNotSame = firstDate !== secondDate
    println(isNotSame) // true
}


fun main() {
    javaLocalDateComparison()
    println()
    comparisonOfLocalDateInKotlin()
}
