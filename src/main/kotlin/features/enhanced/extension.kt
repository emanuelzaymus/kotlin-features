package features.enhanced

import java.util.WeakHashMap
import kotlin.random.Random

interface Person {
    val id: Long
    val firstName: String
    val lastName: String
}

data class User(
    override val id: Long,
    override val firstName: String,
    override val lastName: String,
) : Person

val Person.initials: String
    get() = "${firstName[0]}.${lastName[0]}."

private val user: Person = User(1, "Janko", "Fric")

fun userWithClassicExtension() {
    println(user)
    // User(id=1, firstName=Janko, lastName=Fric)

    println("Initials: ${user.initials}")
    // Initials: J.F.
}

data class UserWithPhoneNumber(
    private val person: Person,
    val phoneNumber: String,
) : Person by person

fun userWithPhoneNumber() {
    val userWithPhoneNumber = UserWithPhoneNumber(user, "+421 123 456 789")

    println(userWithPhoneNumber.id) // 1
    println(userWithPhoneNumber.firstName) // Janko
    println(userWithPhoneNumber.lastName) // Fric
    println(userWithPhoneNumber.phoneNumber) // +421 123 456 789
}

// region Enhanced Extension Property

private val weakReferenceAges = WeakHashMap<Person, Int>()

var User.age: Int?
    get() = weakReferenceAges[this]
    set(value) {
        weakReferenceAges[this] = value
    }

// endregion

fun userWithEnhancedExtension() {
    val user = User(2, "Jozko", "Mrkvicka")
    println(user.age)
    // null

    user.age = 30
    println(user.age)
    // 30
}

fun moreUsersWithEnhancedExtension() {
    println("Ages count: " + weakReferenceAges.size)
    // Ages count: 0

    val users = List(100_000) { index ->
        User(index.toLong(), "XX", "YY")
    }
    users.forEach {
        it.age = Random.nextInt(1, 100)
    }

    println("Ages count: " + weakReferenceAges.size)
    // Ages count: 100000

    val lastUser = User(3, "Jozko", "Mrkvicka")
    lastUser.age = 29

    println("Ages count: " + weakReferenceAges.size)
    // Ages count: 100001


    repeat(5) {
        System.gc()
    }

    println("Ages count: " + weakReferenceAges.size)
    // Emails count: 93781

    repeat(5) {
        System.gc()
    }

    println("Ages count: " + weakReferenceAges.size)
    // Ages count: 1

    println("Last age: " + lastUser.age)
    // Last age: 29
}


fun main() {
    userWithClassicExtension()
    println()

    userWithPhoneNumber()
    println()

    userWithEnhancedExtension()
    println()

    weakReferenceAges.clear()
    moreUsersWithEnhancedExtension()
}
