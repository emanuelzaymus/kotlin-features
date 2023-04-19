package features.delegation

fun classicAssignment() {
    val millionthPrime = calculateMillionthPrime()
    println(millionthPrime)
    // 15485863
}

fun lazyAssignment() {
    val millionthPrime by lazy {
        println("Calculation started...")
        val result = calculateMillionthPrime()
        println("Calculation finished.")
        result
    }

    println("GET MILLIONTH PRIME")
    println(millionthPrime)
    // GET MILLIONTH PRIME
    // Calculation started...
    // Calculation finished.
    // 15485863
}


fun main() {
    classicAssignment()
    lazyAssignment()
}

fun calculateMillionthPrime(): Long = 15_485_863
