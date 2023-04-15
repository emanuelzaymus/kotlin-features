package features.datetime

import java.time.LocalDate
import java.time.LocalDateTime

fun gettingPropertiesFromLocalDate() {
    val date = LocalDate.of(2023, 8, 1)

    val year = date.year
    val month = date.monthValue
    val day = date.dayOfMonth

    println("Year: $year, Month: $month, Day: $day")
    // Year: 2023, Month: 8, Day: 1
}

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int,
)

fun destructuringPoint3D() {
    val point3D = Point3D(1, 2, 3)

    val a = point3D.x
    val b = point3D.y
    val c = point3D.z

    println("a: $a, b: $b, c: $c")
    // a: 1, b: 2, c: 3

    // Destructuring declaration
    var (x, y, z) = point3D

    println("x: $x, y: $y, z: $z")
    // x: 1, y: 2, z: 3

    x = point3D.component1()
    y = point3D.component2()
    z = point3D.component3()

    println("x: $x, y: $y, z: $z")
    // x: 1, y: 2, z: 3
}

fun destructuringLocalDate() {
    val date = LocalDate.of(2023, 9, 20)

    val (year, month, day) = date

    println("Year: $year, Month: $month, Day: $day")
    // Year: 2023, Month: 9, Day: 20
}

operator fun LocalDate.component1(): Int = this.year
operator fun LocalDate.component2(): Int = this.monthValue
operator fun LocalDate.component3(): Int = this.dayOfMonth

fun destructuringLocalDateTime() {
    val dateTime = LocalDateTime.of(2023, 9, 20, 12, 30, 5, 300)

    val (year, month, day, hour, minute, second, nano) = dateTime

    println("Year: $year, Month: $month, Day: $day, Hour: $hour, Minute: $minute, Second: $second, Nano: $nano")
    // Year: 2023, Month: 9, Day: 20, Hour: 12, Minute: 30, Second: 5, Nano: 300
}

operator fun LocalDateTime.component1(): Int = this.year
operator fun LocalDateTime.component2(): Int = this.monthValue
operator fun LocalDateTime.component3(): Int = this.dayOfMonth
operator fun LocalDateTime.component4(): Int = this.hour
operator fun LocalDateTime.component5(): Int = this.minute
operator fun LocalDateTime.component6(): Int = this.second
operator fun LocalDateTime.component7(): Int = this.nano


fun main() {
    gettingPropertiesFromLocalDate()
    destructuringPoint3D()
    destructuringLocalDate()
    destructuringLocalDateTime()
}
