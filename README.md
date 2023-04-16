# kotlin-features

Showcase of uncommon usage of typical Kotlin features.

### TODO:

**LocalDate/LocalDateTime**

- // LocalDate/LocalDateTime -> extension functions for destructuring declaration
- // Simple DSL for creating of LocalDate and LocalDateTime: `17 January 2024 at 13:40`
- // Arithmetics for +/- LocalDateTime: `date - 14.minutes + 50.seconds`
- // fromDate..toDate operator (for-loop, then with List(5) { i -> }); fromDate downTo toDate step 2
- // comparison of localDates
- dateTime in date operator
- Random.nextLocalDate(from = MIN, to = MAX) -> random in dateRange

**Type-safe builders**

- by lazy { heavyCompute() }
- val sql by SimpleSql.select("*").from("user").where("id > 1000")
- by getValue ... -> by EmailAddress("zly", "pes", "prosoft.sk")
    - zly.pes@prosoft.sk

**Enhanced Extension Properties**

- WeakReferenceHashMap -> Extensions properties
