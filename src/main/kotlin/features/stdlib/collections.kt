package features.stdlib

import kotlin.math.pow

fun slice() {
    val list = listOf('a', 'b', 'c', 'd', 'e')

    val slice: List<Char> = list.slice(1..3) // subList(1, 3)
    println(slice)
    // [b, c, d]

    val indices = listOf(4, 0, 2)
    val slice2: List<Char> = list.slice(indices)
    println(slice2)
    // [e, a, c]
}

fun associate() {
    val list = listOf("Kotlin", "Java", "Scala", "Groovy")

    val map: Map<Int, String> = list.associate { it.length to it.uppercase() }
    println(map)
    // {6=GROOVY, 4=JAVA, 5=SCALA}


    val map2 = list.associateBy { it.length }
    println(map2)
    // {6=Groovy, 4=Java, 5=Scala}


    val map3 = list.associateWith { it.length }
    println(map3)
    //{Kotlin=6, Java=4, Scala=5, Groovy=6}
}

fun groupBy() {
    val list = listOf("Kotlin", "Java", "Scala", "Groovy")

    val map: Map<Int, List<String>> = list.groupBy { it.length }
    println(map)
    // {6=[Kotlin, Groovy], 4=[Java], 5=[Scala]}

    val counts = list.groupingBy { it.length }.eachCount()
    println(counts)
    // {6=2, 4=1, 5=1}

    val aggregate = list
        .groupingBy { it.length }
        .aggregate { key: Int, accumulator: String?, element: String, first: Boolean ->
            val acc =
                if (first) "With length $key is "
                else "$accumulator and "
            acc + element.uppercase()
        }

    println(aggregate.values)
    // [With length 6 is KOTLIN and GROOVY, With length 4 is JAVA, With length 5 is SCALA]
}

fun chunked() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val chunks = list.chunked(3)
    println(chunks)
    // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
}

fun windowed() {
    val list = listOf(1, 2, 3, 4, 5)

    val windows = list.windowed(3)
    println(windows)
    // [[1, 2, 3], [2, 3, 4], [3, 4, 5]]

    val partialWindows = list.windowed(3, partialWindows = true)
    println(partialWindows)
    // [[1, 2, 3], [2, 3, 4], [3, 4, 5], [4, 5], [5]]

    val windowsWithStep = list.windowed(3, step = 2, partialWindows = true)
    println(windowsWithStep)
    // [[1, 2, 3], [3, 4, 5], [5]]
}

fun joinToString() {
    val list = listOf(1, 2, 3, 4, 5)

    val string = list.joinToString()
    println(string)
    // 1, 2, 3, 4, 5

    val string2 = list.joinToString(separator = "; ")
    println(string2)
    // 1; 2; 3; 4; 5

    val string3 = list.joinToString(separator = "; ", prefix = "<", postfix = ">")
    println(string3)
    // <1; 2; 3; 4; 5>

    val string4 = list.joinToString(separator = "; ", prefix = "<", postfix = ">", limit = 3, truncated = "...")
    println(string4)
    // <1; 2; 3; ...>

    val string5 = list.joinToString(
        separator = "; ",
        prefix = "<",
        postfix = ">",
        limit = 3,
        truncated = "...",
    ) {
        "$it * 3 = ${it * 3}"
    }
    println(string5)
    // <1 * 3 = 3; 2 * 3 = 6; 3 * 3 = 9; ...>
}

fun joinTo(buffer: Appendable = StringBuilder("Calculation... ")) {
    val list = listOf(1, 2, 3, 4, 5)

    list.joinTo(
        buffer,
        separator = " + ",
        prefix = "Sum: ",
        postfix = " is ${list.sum()}",
    )

    println(buffer)
    // Calculation... Sum: 1 + 2 + 3 + 4 + 5 is 15
}

fun zip() {
    val list = listOf(1, 2, 3, 4, 5)
    val names = listOf("one", "two", "three")

    val zipped: List<Pair<Int, String>> = list.zip(names)
    println(zipped)
    // [(1, one), (2, two), (3, three)]

    val zipWithNext = list.zipWithNext()
    println(zipWithNext)
    // [(1, 2), (2, 3), (3, 4), (4, 5)]
}

fun reduce() {
    val list = listOf(1, 2, 3, 4, 5)

    val product: Int = list.reduce { acc, num -> acc * num }
    println(product)
    // 120

    val product2 = list.reduce(Int::times)
    println(product == product2)
    // true

    val runningReduce: List<Int> = list.runningReduce(Int::times)
    println(runningReduce)
    // [1, 2, 6, 24, 120]

    val emptyList = listOf<Int>()
    try {
        emptyList.reduce(Int::times)
    } catch (_: UnsupportedOperationException) {
    } // UnsupportedOperationException: Empty collection can't be reduced.
}

fun fold() {
    val list = listOf<Int>()

    val foldInt: Int = list.fold(1, Int::times)
    println(foldInt)
    // 1

    val foldDouble: Double = list.fold(1.0, Double::times)
    println(foldDouble)
    // 1.0

    val list2 = listOf("5", "8", "3", "9", "4")

    // 5^1 + 8^2 + 3^3 + 9^4 + 4^5
    val sumOfPowers = list2.foldIndexed(0.0) { index: Int, acc: Double, str: String ->
        acc + str.toDouble().pow(index + 1)
    }
    println(sumOfPowers)
    // 7681.0

    val sumOfPowers2 = list2.asSequence()
        .map { it.toDouble() }
        .mapIndexed { index, num -> num.pow(index + 1) }
        .sum()

    println(sumOfPowers == sumOfPowers2)
    // true
}


// + - union intersect subtract

// asSequence


// utility functions

// repeat(5) {}, measureTime {}, check, require, error

// runCatching{}

//fun utilities() {
//    val list = listOf(null, "b", "C", null, "EF", "g", null)
//
//    list.first() // can throw NoSuchElementException
//    list.first { it?.length == 2 }
//
//    list.firstOrNull()
//    list.firstOrNull { it?.length == 3 }
//
//    val s: String = list.firstNotNullOf { it?.uppercase() }
//
//    list.firstNotNullOf { it ?: "C" }
//}

fun other() {
    val list = listOf(1, 2, 3, 4, 5)

    list.first()
    list.last()
    list.firstOrNull()
    list.lastOrNull()
    list.single()
    list.singleOrNull()
    list.elementAt(2)
    list.elementAtOrElse(10) { 0 }
    list.elementAtOrNull(10)
    list.indexOf(3)
    list.indexOfFirst { it % 2 == 0 }
    list.indexOfLast { it % 2 == 0 }
    list.last { it % 2 == 0 }
    list.lastOrNull { it % 2 == 0 }
    list.first { it % 2 == 0 }
    list.firstOrNull { it % 2 == 0 }
    list.find { it % 2 == 0 }
    list.findLast { it % 2 == 0 }
    list.count { it % 2 == 0 }
    list.contains(3)
    list.containsAll(listOf(1, 2))
    list.isEmpty()
    list.isNotEmpty()
    list.none()
    list.any()
    list.all { it > 0 }
    list.sum()
    list.sumOf { it * 2 }
    list.sumOf { it.toDouble() }
    list.minOrNull()
    list.maxOrNull()
    list.minOf { it * 2 }
    list.maxOf { it * 2 }
    list.minWith(compareBy { it % 2 })
    list.maxWith(compareBy { it % 2 })
    list.sorted()
    list.sortedDescending()
    list.sortedBy { it % 2 }
    list.sortedByDescending { it % 2 }
    list.sortedWith(compareBy { it % 2 })
    list.reversed()
    list.shuffled()
    list.distinct()
    list.distinctBy { it % 2 }
    list.distinctBy { it % 2 }
    list.distinctBy { it % 2 }
    list.distinctBy { it % 2 }
}

fun main() {
    slice()
    associate()
    groupBy()
    chunked()
    windowed()
    joinToString()
    joinTo()
    zip()
    reduce()
    fold()
    other()
}
