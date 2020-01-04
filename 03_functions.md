# Chapter 3: Functions

Creating collections:

```kotlin
fun main() {
	val set = hashSetOf(1, 7 ,53)
	val list = arrayListOf(1, 7, 53)
	val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

	println(set.javaClass)
	println(list.javaClass)
	println(map.javaClass)
}
```

Basic operations:

```kotlin
fun main() {
	val strings = listOf("first", "second", "fourteenth")
	println(strings.last())

	val numbers = setOf(1, 2, 3, 1)
	println(numbers.max())
}
```

`joinToString` implementation:

```kotlin
import java.lang.StringBuilder

fun <T> joinToString(
	collection: Collection<T>,
	separator: String = ", ",
	prefix: String = "",
	postfix: String = ""
): String {
	val result = StringBuilder(prefix)
	for ((index, element) in collection.withIndex()) {
		if (index > 0) result.append(separator)
		result.append(element)
	}
	result.append(postfix)
	return result.toString()
}

fun main() {
	val list = listOf(1, 2, 3)
	println(joinToString(list, "; ", "(", ")"))
}
```

Adding method:

```kotlin
fun String.lastChar(): Char = this.get(this.length - 1)

fun main() {
	println("Kotlin".lastChar())
}
```

Declare extension function of collection:

```kotlin
import java.lang.StringBuilder

fun <T> Collection<T>.joinToString(
	separator: String = ", ",
	prefix: String = "",
	postfix: String = ""
): String {
	val result = StringBuilder(prefix)
	for ((index, element) in this.withIndex()) {
		if (index > 0) result.append(separator)
		result.append(element)
	}
	result.append(postfix)
	return result.toString()
}

fun main() {
	val list = listOf(1, 2, 3)
	println(list.joinToString("; ", "(", ")"))
}

```

Declaring a mutable extension property:

```kotlin
import java.lang.StringBuilder

var StringBuilder.lastChar: Char
	get() = get(length - 1)
	set(value: Char) = setCharAt(length - 1, value)

fun main() {
	val sb = StringBuilder("Kotlin?")
	sb.lastChar = '!'
	println(sb)
}
```
