# Chapter 6: The Kotlin type system


```kotlin
fun strLen(s: String?) = s?.length ?: 0

fun main() {
	println(strLen("100"))
	println(strLen(null))
}
```

Safe-call operator `?.`:

```kotlin
fun printAllCaps (s: String?) {
	val allCaps: String? = s?.toUpperCase()
	println(allCaps)
}

fun main() {
	printAllCaps("hello")
	printAllCaps(null)
}
```

Chaining multiple safe-calls operator:

```kotlin
class Address(val streetAddress: String, val zipCode: Int,
	val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
	val country = this.company?.address?.country
	return if (country != null) country else "Unknown"
}

fun main() {
	val person = Person("Dimitry", null)
	println(person.countryName())
}
```
