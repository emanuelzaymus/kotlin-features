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

fun partition() {
    val list = listOf(1, 2, 3, 4, 5)

    val (even, odd) = list.partition { it % 2 == 0 }
    println(even) // [2, 4]
    println(odd)  // [1, 3, 5]
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

fun scan() {
    val list = listOf(1, 2, 3, 4, 5)

    val scan: List<Double> = list.scan(1.0, Double::times) // runningFold
    println(scan)
    // [1.0, 1.0, 2.0, 6.0, 24.0, 120.0]
}

fun operations() {
    val listA = listOf(3, 1, 2, 3, 4, 5)
    val listB = listOf(3, 4, 5, 6, 7)

    val aPlusB: List<Int> = listA + listB
    println(aPlusB)
    // [1, 2, 3, 4, 5, 3, 4, 5, 6, 7]


    val aMinusB: List<Int> = listA - listB
    println(aMinusB)
    // [1, 2]

    val bMinusA: List<Int> = listB - listA
    println(bMinusA)
    // [6, 7]


    val aSubtractB: Set<Int> = listA subtract listB
    println(aSubtractB)
    // [1, 2]

    val bSubtractA: Set<Int> = listB subtract listA
    println(bSubtractA)
    // [6, 7]


    val union: Set<Int> = listA union listB
    println(union)
    // [1, 2, 3, 4, 5, 6, 7]

    val intersect: Set<Int> = listA intersect listB
    println(intersect)
    // [3, 4, 5]

    val difference: Set<Int> = listA difference listB
    println(difference)
    // [1, 2, 6, 7]
}

infix fun <T> Iterable<T>.difference(other: Iterable<T>): Set<T> {
    val leftOnly = this subtract other.toSet()
    val rightOnly = other subtract this.toSet()

    return leftOnly union rightOnly
}

fun utilities() {
    val list = listOf(5, 9, 23, 14, 5, 14, 9, 0, 1, -1)

    list.distinct()
        .also {
            println(it)  // [5, 9, 23, 14, 0, 1, -1]
        }
        .sorted()
        .also {
            println(it) // [-1, 0, 1, 5, 9, 14, 23]
        }
        .reversed()
        .also {
            println(it) // [23, 14, 9, 5, 1, 0, -1]
        }
        .shuffled()
        .also {
            println(it) // [14, 23, 1, -1, 5, 0, 9]
        }
        .sortedDescending()
        .also {
            println(it) // [23, 14, 9, 5, 1, 0, -1]
        }

    println(list.random())
    // 14

    println(list.average())
    // 7.9

    println(list.minOf { -it })
    // -23

    println(list.maxOf { -it })
    // -1
}


fun main() {
    slice()
    associate()
    groupBy()
    chunked()
    windowed()
    partition()
    joinToString()
    joinTo()
    zip()
    reduce()
    fold()
    scan()
    operations()
    utilities()
}
