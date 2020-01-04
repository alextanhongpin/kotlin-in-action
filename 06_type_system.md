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

Elvis operator `?:`:

```kotlin
fun strLenSafe(s: String?): Int = s?.length ?: 0

fun main() {
	println(strLenSafe("hello"))
	println(strLenSafe(null))
}
```


Using `throw` together with Elvis operator:

```kotlin
class Address(val streetAddress: String, val zipCode: Int,
	val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
	val address = person.company?.address ?: 
		throw IllegalArgumentException("No address")
	with(address) {
		println(streetAddress)
		println("$zipCode $city, $country")
	}
}

fun main() {
	val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
	val jetbrains = Company("Jetbrains", address)
	val person = Person("Dimitry", jetbrains)

	printShippingLabel(person)
	printShippingLabel(Person("Alexy", null))
}
```

Safe casts `as?`:

```kotlin
class Person(val firstName: String, val lastName: String) {
	override fun equals(o: Any?): Boolean {
		val otherPerson = o as? Person ?: return false

		return otherPerson.firstName == firstName &&
		otherPerson.lastName == lastName
	}

	override fun hashCode(): Int = 
		firstName.hashCode() * 37 + lastName.hashCode()
}

fun main() {
	val p1 = Person("John", "Doe")
	val p2 = Person("John", "Doe")
	println(p1 == p2)

	println(p1.equals(42))
}
```


Not-null assertions `!!`

```kotlin
fun ignoreNulls(s: String?) {
	val sNotNull = s!!
	println(s)
}

fun main() {
	ignoreNulls(null)
}
```

The `let` function:

```kotlin
fun sendEmailTo(email: String) {
	println("Sending email to $email")
}

fun main() {
	var email: String? = "john.doe@mail.com"
	if (email != null) sendEmailTo(email)
	email?.let { sendEmailTo(it) }

	email = null
	email?.let { sendEmailTo(it) }
}
```
