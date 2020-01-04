# Chapter 5: Lambdas

```kotlin
data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
	var maxAge = 0
	var theOldest: Person? = null

	for (person in people) {
		if (person.age > maxAge) {
			maxAge = person.age
			theOldest = person
		}
	}
	println(theOldest)
}

fun main() {
	val people = listOf(Person("Alice", 29), Person("Bob", 31))
	println(people.maxBy{ it.age })
	println(people.maxBy(Person::age))
	findTheOldest(people)
}
```

Calling lambda stored in variable:

```kotlin
fun main() {
	val sum = { x: Int, y: Int -> x + y }
	println(sum(1, 2))
}
```

Member reference syntax:

```kotlin
fun main() {
	fun salute() = println("Salute!")
	run(::salute)
}
```

`map` and `filter`:

```kotlin
class Person(val name: String, val age: Int)

fun main() {
	val list = listOf(1, 2, 3, 4)
	println(list.filter { it % 2 == 0 })
	println(list.map { it * it })

	val people = listOf(Person("Alice", 29), Person("Bob", 31))
	println(people.filter { it.age > 30 })
	println(people.map { it.name })
	println(people.map(Person::name))
    
   	val numbers = mapOf(0 to "zero", 1 to "one")
	println(numbers.map { it.value.toUpperCase() })
	println(numbers.mapValues { it.value.toUpperCase() })
    println(numbers.map { it.key + 10 })
    println(numbers.mapKeys { it.key + 10 })
    println(numbers.filterValues { it == "zero" })
}
```

`all`, `any`, `count` and `find`:

```kotlin
class Person(val name: String, val age: Int)

fun main() {
	val canBeInClub27 = { p: Person -> p.age <= 27 }
	val people = listOf(Person("Alice", 27), Person("Bob", 31), Person("Carol", 31))
	println(people.all(canBeInClub27))
	println(people.any(canBeInClub27))
	println(people.count(canBeInClub27))
	println(people.find(canBeInClub27))
	println(people.groupBy { it.age })

	val list = listOf("a", "ab", "b")
	println(list.groupBy(String::first))
}
```

`flatMap` and `flatten`:

```kotlin
class Book(val title: String, val authors: List<String>)

fun main() {
	val strings = listOf("abc", "def")
	println(strings.flatMap { it.toList() })

	val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")),
                       Book("Mort", listOf("Terry Pratchett")),
                       Book("Good Omens", listOf("Terry Pratchett", 
                                                 "Neil Gaiman")))
	println(books.flatMap { it.authors }.toSet())
}
```

Sequence:

```kotlin
class Person(val name: String, val age: Int)

fun main() {
	val people = listOf(Person("Alice", 27), Person("Bob", 31), Person("Carol", 31))
	println(people.map(Person::name).filter { it.startsWith("A") })
	println(people.asSequence().map(Person::name).filter { it.startsWith("A") }.toList())
    
	people
    .map{
        println("mapping: $it")
        it.name
    }
    .filter{
        println("filter: $it")
        it.startsWith("A")
    }
    
	people
    .asSequence()
    .map{
        println("mapping: $it")
        it.name
    }
    .filter{
        println("filter: $it")
        it.startsWith("A")
    }.toList()
}
```


`with` and `apply`:

```kotlin
fun alphabet(): String {
	val result = StringBuilder()
	for (letter in 'A'..'Z') {
		result.append(letter)
	}
	result.append("\nNow I know the alphabet")
	return result.toString()
}

fun alphabet2(): String {
	val stringBuilder = StringBuilder()
	return with(stringBuilder) {
		for (letter in 'A'..'Z') {
			this.append(letter)
		}
		append("\nNow I know the alphabet")
		this.toString()
	}
}

fun alphabet3() = with(StringBuilder()) {
	for (letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know the alphabet")
	toString()
}

fun alphabet4() = StringBuilder().apply{
	for (letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know the alphabet")
}.toString()

fun main() {
	println(alphabet())
	println(alphabet2())
	println(alphabet3())
	println(alphabet4())
}
```
