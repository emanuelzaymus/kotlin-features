package features.delegation

import kotlin.reflect.KProperty

private var email: String? = null
    get() {
        println("Somebody is getting variable 'email': $field")
        return field
    }
    set(value) {
        println("Somebody is setting variable 'email' with: $value")
        field = value?.lowercase()
    }

fun emailVariable() {
    println(email)
    // Somebody is getting variable 'email': null
    // null

    email = "Peto.MargetiaKt@prosoft.sk"
    // Somebody is setting variable 'email' with: Peto.MargetiaKt@prosoft.sk

    println(email)
    // Somebody is getting variable 'email': peto.margetiakt@prosoft.sk
    // peto.margetiakt@prosoft.sk
}

class Email(email: String? = null) {

    var value: String? = email
        get(): String? {
            println("Somebody is getting class 'Email': $field")
            return field
        }
        set(newValue) {
            println("Somebody is setting class 'Email' with: $newValue")
            field = newValue?.lowercase()
        }

}

fun emailClass() {
    val email = Email()

    println(email.value)
    // Somebody is getting class 'Email': null
    // null

    email.value = "Miso.JostiaKt@sybrisoft.sk"
    // Somebody is setting class 'Email' with: Miso.JostiaKt@sybrisoft.sk

    println(email.value)
    // Somebody is getting class 'Email': miso.jostiakt@sybrisoft.sk
    // miso.jostiakt@sybrisoft.sk
}

class EmailDelegate(private var value: String? = null) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        println("$thisRef is getting delegate '${property.name}': $value")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String?) {
        println("$thisRef is setting delegate '${property.name}' with: $newValue")
        value = newValue?.lowercase()
    }

}

fun emailDelegate() {
    var delegatedEmail: String? by EmailDelegate()

    println(delegatedEmail)
    // null is getting delegate 'delegatedEmail': null
    // null

    delegatedEmail = "VladiKt.HanusniaKt@prosoft.sk"
    // null is setting delegate 'delegatedEmail' with: VladiKt.HanusniaKt@prosoft.sk

    println(delegatedEmail)
    // null is getting delegate 'delegatedEmail': vladikt.hanusniakt@prosoft.sk
    // vladikt.hanusniakt@prosoft.sk
}

class User(private val name: String) {

    var userEmail: String? by EmailDelegate()

    override fun toString(): String = name

}

fun emailDelegateInClass() {
    val user = User("Psisko")

    println(user.userEmail)
    // Psisko is getting delegate 'userEmail': null
    // null

    user.userEmail = "zly.pes@prosoft.kt"
    // Psisko is setting delegate 'userEmail' with: zly.pes@prosoft.kt

    println(user.userEmail)
    // Psisko is getting delegate 'userEmail': zly.pes@prosoft.kt
    // zly.pes@prosoft.kt
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
