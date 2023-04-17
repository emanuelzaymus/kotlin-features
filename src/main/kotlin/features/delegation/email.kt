package features.delegation

import kotlin.reflect.KProperty

private var email: String? = null
    get() {
        println("Somebody is getting 'email' variable: $field")
        return field
    }
    set(value) {
        println("Somebody is setting 'email' variable with: $value")
        field = value
    }

fun emailVariable() {
    println("Email is: $email")
    // Somebody is getting 'email' variable: null
    // Email is: null

    email = "janko.fric@prosoft.sk"
    // Somebody is setting 'email' variable with: janko.fric@prosoft.sk

    println("Email after change is: $email")
    // Somebody is getting 'email': janko.fric@prosoft.sk
    // Email after change is: janko.fric@prosoft.sk
}

class Email(email: String = "<empty-email>") {

    var value: String = email
        get(): String {
            println("Somebody is getting 'Email' class: $field")
            return field
        }
        set(newValue) {
            println("Somebody is setting 'Email' class with: $newValue")
            field = newValue
        }

}

fun emailClass() {
    val email = Email()

    println("Email class is: ${email.value}")
    // Somebody is getting 'Email' class: <empty-email>
    // Email class is: <empty-email>

    email.value = "fric.janko@prosoft.sk"
    // Somebody is setting 'Email' class with: fric.janko@prosoft.sk

    println("Email class after change is: ${email.value}")
    // Somebody is getting 'Email' class: fric.janko@prosoft.sk
    // Email class after change is: fric.janko@prosoft.sk
}

class EmailDelegate(email: String = "<empty-email>") {
    private var value: String = email
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("$thisRef is getting '${property.name}' delegate: $value")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        println("$thisRef is setting '${property.name}' delegate with: $newValue")
        value = newValue
    }

}

fun emailDelegate() {
    var delegatedEmail: String by EmailDelegate()

    println("Delegated email: $delegatedEmail")
    // null is getting 'delegatedEmail' delegate: <empty-email>
    // Delegated email: <empty-email>

    delegatedEmail = "zly.pes@prosoft.sk"
    // null is setting 'delegatedEmail' delegate with: zly.pes@prosoft.sk

    println("Delegated email after change: $delegatedEmail")
    // null is getting 'delegatedEmail' delegate: zly.pes@prosoft.sk
    // Delegated email after change: zly.pes@prosoft.sk
}

class User(private val name: String) {

    var userEmail: String by EmailDelegate()

    override fun toString(): String = name

}

fun emailDelegateInClass() {
    val user = User("Psisko")

    println("User's email: ${user.userEmail}")
    // Psisko is getting 'userEmail' delegate: <empty-email>
    // User's email: <empty-email>

    user.userEmail = "zly.pes@prosoft.sk"
    // Psisko is setting 'userEmail' delegate with: zly.pes@prosoft.sk

    println("User's email after change: ${user.userEmail}")
    // Psisko is getting 'userEmail' delegate: zly.pes@prosoft.sk
    // User's email after change: zly.pes@prosoft.sk
}

fun main() {
    emailVariable()
    println()

    emailClass()
    println()

    emailDelegate()
    println()

    emailDelegateInClass()
}
