# kotlin-features

### _Showcase of uncommon usage of typical Kotlin features_

___

### LocalDate / LocalDateTime

- Extension functions for destructuring declaration
- Simple DSL for creating of LocalDate and LocalDateTime: `17 January 2024 at 13:40 and 50.seconds`
- Arithmetics for LocalDateTime: `dateTime - 14.minutes + 50.seconds`
- LocalDate range: `fromDate..toDate`, `fromDate unitl toDate`, `fromDate downTo toDate step 2`
- Comparison of LocalDates: > < >= <= == != === !==
- LocalDateTime `in` LocalDate
- `fun Random.Default.nextLocalDate(from = LocalDate.MIN, to = LocalDate.MAX): LocalDate`

### Delegation

- Lazy function `by lazy { }`
- Example for implementing `by EmailAddress()`
- QueryBuilder example

### Enhanced Extension Properties

- Extensions properties using WeakHashMap for storing data

### _Showcase of interesting functions from Kotlin Standard Library_

___

### Build functions

- `buildList`
- `buildSet`
- `buildMap`
- `buildString`

### Collection functions

- `slice`
- `associate`, `associateBy`, `associateWith`
- `groupBy`, `groupingBy`
- `chunked`
- `windowed`
- `partition`
- `joinToString`, `joinTo`
- `zip`, `unzip`, `zipWithNext`
- `reduce`, `runningReduce`
- `fold`, `scan`
- `subtract`, `union`, `intersect`

### Measuring time

- `measureTime { }`
- `measureTimedValue { }`
- `markNow()`

### Utilities

- `require`, `requireNotNull`
- `check`, `checkNotNull`
- `error`
- `repeat`, `use`
- `Delegates.observable`, `Delegates.vetoable`
