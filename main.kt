fun main(args: Array<String>) {
    println("hello world")
}


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
