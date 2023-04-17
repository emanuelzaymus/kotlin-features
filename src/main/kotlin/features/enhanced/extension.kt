package features.enhanced

import java.util.WeakHashMap

interface User {
    val id: Long
    val firstName: String
    val lastName: String
}

data class ProUser(
    override val id: Long,
    override val firstName: String,
    override val lastName: String,
) : User

val User.initials: String
    get() = "${firstName[0]}.${lastName[0]}"

private val user: User = ProUser(1, "Janko", "Fric")

fun userWithClassicExtension() {
    println("User: $user")
    // User: ProUser(id=1, firstName=Janko, lastName=Fric)

    println("Initials: ${user.initials}")
    // Initials: J.F
}

data class UserWithEmail(
    private val user: User,
    val email: String,
) : User by user

fun userWithEmail() {
    val userWithEmail = UserWithEmail(user, "janko.fric@prosoft.sk")

    println(userWithEmail.id) // 1
    println(userWithEmail.firstName) // Janko
    println(userWithEmail.lastName) // Fric
    println(userWithEmail.email) // janko.fric@prosoft.sk
}

private val weakReferenceEmails = WeakHashMap<User, String>()

var User.email: String?
    get() = weakReferenceEmails[this]
    set(value) {
        weakReferenceEmails[this] = value
    }

fun userWithEnhancedExtension() {
    val proUser = ProUser(2, "Jozko", "Mrkvicka")
    println("Users email: " + proUser.email)
    // Users email: null

    proUser.email = "jozko.mrkvicka@prosoft.sk"
    println("Users email after update: " + proUser.email)
    // Users email after update: jozko.mrkvicka@prosoft.sk
}

fun moreUsersWithEnhancedExtension() {
    println("Emails count: " + weakReferenceEmails.size)
    // Emails count: 0

    val users = List(100_000) { index ->
        ProUser(index.toLong(), "Jozko", "Mrkvicka")
    }
    users.forEach {
        it.email = "${it.firstName}.${it.lastName}@prosoft.sk"
    }

    println("Emails count: " + weakReferenceEmails.size)
    // Emails count: 100000

    val lastUser = ProUser(3, "Janko", "Hrasko")
    lastUser.email = "janko.hrasko@prosoft.sk"

    println("Emails count: " + weakReferenceEmails.size)
    // Emails count: 100001


    repeat(5) {
        System.gc()
    }

    println("Emails count: " + weakReferenceEmails.size)
    // Emails count: 93781

    repeat(5) {
        System.gc()
    }

    println("Emails count: " + weakReferenceEmails.size)
    // Emails count: 1

    println("Last email: " + lastUser.email)
    // Last email: janko.hrasko@prosoft.sk
}


fun main() {
    userWithClassicExtension()
    println()
    userWithEmail()
    println()
    userWithEnhancedExtension()
    println()
    moreUsersWithEnhancedExtension()
}
