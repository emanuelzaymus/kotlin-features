package features.stdlib

fun listOfFunction() {
    val list: List<Int> = listOf(1, 2, 3, 4, 5)

    println(list)
    // [1, 2, 3, 4, 5]
}

fun listFunction() {
    val list: List<Int> = List(5) { i -> i + 1 }

    println(list)
    // [1, 2, 3, 4, 5]
}

fun mutableListOfFunction() {
    val mutableList: MutableList<Int> = mutableListOf()

    mutableList.add(1)
    for (i in 2..4) {
        mutableList.add(i)
    }
    mutableList.remove(3)
    mutableList.add(5)

    println(mutableList)
    // [1, 2, 4, 5]
}

fun buildListFunction() {
    val list: List<Int> = buildList {
        add(1)
        for (i in 2..4) {
            add(i)
        }
        remove(3)
        add(5)
    }

    println(list)
    // [1, 2, 4, 5]

    try {
        (list as MutableList).add(6)
    } catch (_: UnsupportedOperationException) {
    }
}

fun otherBuildFunctions() {
    val set: Set<Int> = buildSet {
        add(1)
        add(1)
        add(2)
    }
    println(set)
    // [1, 2]


    val map = buildMap {
        put(1, "one")
        put(2, "two")
    }
    println(map)
    // {1=one, 2=two}


    val string = buildString {
        append("Hello, ")
        append("World!")
    }
    println(string)
    // "Hello, World!"
}

fun main() {
    listOfFunction()
    listFunction()
    mutableListOfFunction()
    buildListFunction()
    otherBuildFunctions()
}
