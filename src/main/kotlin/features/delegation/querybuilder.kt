package features.delegation

import kotlin.reflect.KProperty

// DB table
private const val USERS = "users"

// Columns
private const val ID = "id"
private const val FULL_NAME = "full_name"
private const val EMAIL = "email"

fun classicSqlBuilder() {
    val query: String = QueryBuilder
        .select(ID, FULL_NAME, EMAIL)
        .from(USERS)
        .where("$ID > 4")
        .build()

    println(query)
    // SELECT id, full_name, email FROM users WHERE id > 4
}

interface QueryStatementBuilder {
    fun build(): String
}

fun delegatedSqlBuilder() {
    val query: String by QueryBuilder
        .select(ID, FULL_NAME, EMAIL)
        .from(USERS)
        .where("$ID > 4")

    println(query)
    // SELECT id, full_name, email FROM users WHERE id > 4
}

operator fun QueryStatementBuilder.getValue(thisRef: Any?, property: KProperty<*>): String = this.build()


object QueryBuilder {
    fun select(vararg columns: String) = SelectStatementBuilder(columns)
}

class SelectStatementBuilder(private val columns: Array<out String>) : QueryStatementBuilder {
    fun from(table: String) = FromStatementBuilder(this, table)
    override fun build(): String = "SELECT ${columns.joinToString()}"
}

class FromStatementBuilder(
    private val selectStatementBuilder: SelectStatementBuilder,
    private val table: String,
) : QueryStatementBuilder {
    fun where(condition: String) = WhereStatementBuilder(this, condition)
    override fun build(): String = selectStatementBuilder.build() + " FROM $table"
}

class WhereStatementBuilder(
    private val fromStatementBuilder: FromStatementBuilder,
    private val condition: String,
) : QueryStatementBuilder {
    override fun build(): String = fromStatementBuilder.build() + " WHERE $condition"
}


fun main() {
    classicSqlBuilder()
    println()
    delegatedSqlBuilder()
}
