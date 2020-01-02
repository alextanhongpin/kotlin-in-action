# Chapter 2: Kotlin Basics


Basic function:
```kotlin
fun max(a: Int, b: Int): Int =
    if (a > b) a else b

fun main(args: Array<String>) {
    println(max(10, 1))
}
```

Basic class:
```kotlin
class Person(val name: String)

fun main() {
    val person = Person("john")
    println(person.name)
}
```

Custom accessors:

```kotlin
class Rectangle(
	val width: Int,
	val height: Int
) {
    val isSquare: Boolean
    	get() = width == height
}

fun main() {
    val square = Rectangle(10, 10)
    println(square.isSquare)
    
  
    val box = Rectangle(10, 20)
    println(box.isSquare)
}
```

## Enums

Basic enums:

```kotlin
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun main() {
    println(Color.RED)
}
```

Declaring an enum class with properties:

```kotlin
enum class Color(
    val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), 
    ORANGE(255, 165, 0), 
    YELLOW(255, 255, 0), 
    GREEN(0, 255, 0), 
    BLUE(0, 0, 255), 
    INDIGO(75, 0, 130), 
    VIOLET(238, 130, 238);
    
    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnenomic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }
  
fun getWarmth(color: Color) = when(color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("dirty color")
    }
    
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == Color.RED && c2 == Color.YELLOW) ||
        (c1 == Color.YELLOW && c2 == Color.RED) ->
        	Color.ORANGE
       
        (c1 == Color.YELLOW && c2 == Color.BLUE) ||
        (c1 == Color.BLUE && c2 == Color.YELLOW) ->
        	Color.GREEN
        
        (c1 == Color.BLUE && c2 == Color.VIOLET) ||
        (c1 == Color.VIOLET && c1 == Color.BLUE) ->
        	Color.INDIGO
        
        else -> throw Exception("dirty color")
    }
    
fun main() {
    println(Color.RED.rgb())
    println(getMnenomic(Color.BLUE))
    println(getWarmth(Color.RED))
    println(mix(Color.RED, Color.YELLOW))
    println(mix(Color.YELLOW, Color.RED))
    println(mixOptimized(Color.RED, Color.YELLOW))
}
```

## Expression class hierachy

```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun main() {
	println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(e: Expr): Int {
	if (e is Num) {
		val n = e as Num
		return n.value
	}
	if (e is Sum) {
		return eval(e.right) + eval(e.left)
	}
	throw IllegalArgumentException("Unknown expression")
}
```

With `when` instead of `if-cascade`:

```kotlin
fun eval(e: Expr): Int =
	when(e) {
		is Num -> e.value
		is Sum -> 
			eval(e.right) + eval(e.left)
		else ->
			throw IllegalArgumentException("Unknown expression")
	}
```

## Ranges and Progressions

```kotlin
fun main() {
    val oneToTen = 1..10
    println(oneToTen.toList())
    
    for (i in 1..10) {
        println(i)
    }
}
```

Iterating over a range with step:

```kotlin
fun main() {
    for (i in 100 downTo 1 step 2) {
        println(i)
    }
}
```

Iterating over maps:

```kotlin
import java.util.TreeMap

fun main() {
	val binaryReps = TreeMap<Char, String>()

	for (c in 'A'..'F') {
		val binary = Integer.toBinaryString(c.toInt())
		binaryReps[c] = binary
	}

	for ((letter, binary) in binaryReps) {
		println("$letter = $binary")
	}
}
```

Iterating over with index:

```kotlin
fun main() {
	val list = arrayListOf("10", "11", "1000")
	for ((index, element) in list.withIndex()) {
		println("$index: $element")
	}
}
```

Using `in` to check collection and range membership:

```kotlin
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun main() {
	println(isLetter('q'))
	println(isNotDigit('x'))
}
```

With `when` branches:

```kotlin
fun recognize(c: Char) = when(c) {
	in '0'..'9' -> "It's a digit!"
	in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
	else -> "I don't know..."
}

fun main() {
	println(recognize('a'))
	println(recognize('0'))
	println(recognize('!'))
}
```

## Exceptions

`Try` as an expression:
```kotlin
import java.io.BufferedReader
import java.io.StringReader


fun readNumber(reader: BufferedReader) {
	val number = try {
		Integer.parseInt(reader.readLine())
	} catch(e: NumberFormatException) {
		return
	}
	println(number)
}

fun main() {
	val reader = BufferedReader(StringReader("not a number"))
	readNumber(reader)
    println("done")
}

```
